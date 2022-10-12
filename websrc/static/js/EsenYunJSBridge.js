;(function() {
  if (window.EsenYunJSBridge) {
    // Android加上了这个if判断，如果当前window已经定义了EsenyunBridge对象，不再重新加载
    // 避免重新初始化_callback_map等变量，导致之前的消息回调失败，返回cb404
    //alert('window already has a EsenyunBridge object!!!');
    return
  }

  ///////////////////////////////////本地调用的实际逻辑////////////////////////////////////////////
  var CUSTOM_PROTOCOL_SCHEME = 'esenyun',
    messagingIframe,
    messageHandlers = {},
    sendMessageQueue = [],
    uniqueId = 1,
    QUEUE_HAS_MESSAGE = '__QUEUE_MESSAGE__/',
    callbacksCount = 1,
    callbacks = {},
    myCallbacks = {},
    responseCallbacks = {}

  // 页面模板数据, 表单有效性数据
  var templateContent = '',
    appContent = '',
    validation = {
      isValid: false,
      message: ''
    }

  function _createQueueReadyIframe(doc) {
    messagingIframe = doc.createElement('iframe')
    messagingIframe.style.display = 'none'
    doc.documentElement.appendChild(messagingIframe)
  }

  function registerHandler(handlerName, handler) {
    messageHandlers[handlerName] = handler
  }

  function _handleMessage(callbackId, message) {
    try {
      var callback = callbacks[callbackId]
      if (!callback) return
      callback.apply(null, [message])
    } catch (e) {
      alert(e)
    }
  }

  /**
   * 获取用户ua信息,判断OS
   * @returns {string}
   */
  function getOS() {
    var userAgent = navigator.userAgent
    return userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/) ? 'ios' : userAgent.match(/Android/i) ? 'android' : ''
  }

  /**
   *
   * @returns {Array|{index: number, input: string}}
   */
  // function isCloudHub() {
  //   var userAgent = navigator.userAgent
  //   return userAgent.match(/App\/cloudhub/)
  // }

  //sendMessage add message, 触发native处理 sendMessage
  function _doSend(message, responseCallback) {
    // console.log('fucntionName: _doSend run')

    if (responseCallback) {
      var callbackId = 'cb_' + uniqueId++ + '_' + new Date().getTime()
      responseCallbacks[callbackId] = responseCallback
      message.callbackId = callbackId
    }
    // alert(JSON.stringify(message))
    sendMessageQueue.push(message)
    messagingIframe.src = CUSTOM_PROTOCOL_SCHEME + '://' + QUEUE_HAS_MESSAGE
    // alert(messagingIframe.src)
  }

  // Use this in javascript to request native objective-c code
  // functionName : string (I think the name is explicit :p)
  // args : array of arguments
  // callback : function with n-arguments that is going to be called when the native code returned
  function _call(functionName, message, callback) {
    //只有在手机Esenyun JSBridge
    if (!getOS()) return false
    var hasCallback = callback && typeof callback == 'function'
    var callbackId = hasCallback ? callbacksCount++ : 0

    if (hasCallback) {
      callbacks[callbackId] = callback
    }

    var iframe = document.createElement('IFRAME')
    iframe.setAttribute(
      'src',
      CUSTOM_PROTOCOL_SCHEME + ':' + functionName + ':' + callbackId + ':' + encodeURIComponent(JSON.stringify(message))
    )
    // For some reason we need to set a non-empty size for the iOS6 simulator...
    iframe.setAttribute('height', '1px')
    iframe.setAttribute('width', '1px')
    document.documentElement.appendChild(iframe)
    setTimeout(function() {
      iframe.parentNode.removeChild(iframe)
      iframe = null
    }, 100)
  }

  // 获取从 android/ios 端接入的模板数据
  function getTemplateContent(json) {
    // console.log('%c getOS()', 'color:#09f', getOS(), navigator.userAgent)
    switch (getOS()) {
      case 'android':
        // console.log('%c getTemplateContent-android_callback fired', 'color:#911')
        window.android_callback.onJsonContent(json)
        break
      case 'ios':
        // console.log('%c getTemplateContent-ios_callback fired', 'color:#911')
        window.ios_callback.onJsonContent(json)
        break
      default:
        break
    }
  }

  // 设定模板的内容到 window 域
  function setTemplateContent(content) {
    // console.log('%c TemplateContent:', 'color:#09f', content)
    __EsenYunJSBridge.templateContent = content
    // console.log('%c window TemplateContent:', 'color:#09f', JSON.parse(__EsenYunJSBridge.templateContent))
  }

  // H5 取 APP 的内容
  function getAppContent() {
    // console.log('%c H5 getAppContent:', 'color:#09f', __EsenYunJSBridge.appContent)
    return __EsenYunJSBridge.appContent
  }

  /**
   * 表单是否有效
   * @param {Object} info  { isValid, message }
   */
  function setTemplateValid(info) {
    __EsenYunJSBridge.validation.isValid = info.isValid
    __EsenYunJSBridge.validation.message = info.message
    // console.log('setTemplateValid info:', info)
  }

  /**
   * 给 App 调用的方法
   */

  // 提供给native使用, 原生给H5传递消息
  function _dispatchMessageFromNative(messageJSON) {
    // console.log('native dispatch message run: ', messageJSON)
    setTimeout(function() {
      // alert(JSON.stringify(messageJSON))
      var message = messageJSON
      // console.log(Boolean(message));
      var responseCallback
      // //java call finished, now need to call js callback function
      if (message.responseId) {
        responseCallback = responseCallbacks[message.responseId]
        if (!responseCallback) {
          return
        }
        // console.log('native dispatch rid: ', message.responseId, responseCallback)
        responseCallback(message.responseData)
        delete responseCallbacks[message.responseId]
      } else {
        //直接发送
        if (message.callbackId) {
          var callbackResponseId = message.callbackId
          responseCallback = function(responseData) {
            _doSend({
              responseId: callbackResponseId,
              responseData: responseData
            })
          }
        }
        var handler
        if (message.handlerName) {
          handler = messageHandlers[message.handlerName]
        }
        if (!handler) {
          responseCallback({
            status: '-1'
          })
        }
        //查找指定handler
        try {
          // console.log('responseCallback', responseCallback)
          // console.log('message.data:', message);
          handler(message.data, responseCallback)
        } catch (exception) {
          // if (typeof console != 'undefined') {
          //   console.log('WebViewJavascriptBridge: WARNING: javascript handler threw.', message, exception)
          // }
        }
      }
    })
  }

  // 提供给native调用,该函数作用:获取sendMessageQueue返回给native,由于android不能直接获取返回的内容,所以使用url shouldOverrideUrlLoading 的方式返回内容
  function _fetchQueue() {
    var messageQueueString = JSON.stringify(sendMessageQueue)
    sendMessageQueue = []
    // console.log('fetchQueue run: ', messageQueueString)
    if (getOS() === 'ios') {
      return messageQueueString
    } else {
      //android can't read directly the return data, so we can reload iframe src to communicate with java
      messagingIframe.src = CUSTOM_PROTOCOL_SCHEME + '://_fetchQueue/' + encodeURIComponent(messageQueueString)
    }
  }

  // App 设置页面数据, 用于展示页面
  function appSetContent(content) {
    __EsenYunJSBridge.appContent = content
    // alert('APP 调用 appSetContent 成功')
    // console.log('%c APP appSetContent:', 'color:#09f', content)
    // 调用回调
    if (myCallbacks['updateApprovalDetailContent']) {
      // alert(`appSetContent ${typeof myCallbacks['updateApprovalDetailContent']}`);
      myCallbacks['updateApprovalDetailContent'](content)
    }
    return content
  }

  // App 取 h5 页面数据, 用于上传后台
  function appGetContent() {
    if (__EsenYunJSBridge.validation.isValid) {
      return __EsenYunJSBridge.templateContent
    } else {
      // console.log('validation error:', __EsenYunJSBridge.validation)
      // alert(__EsenYunJSBridge.validation.message);
      return ''
    }
  }

  // 本地用的桥
  function _myCall(functionName, message, callback) {
    //只有在手机Esenyun JSBridge
    if (!getOS()) return false

    var hasCallback = callback && typeof callback == 'function'
    var callbackId = hasCallback ? functionName : 0

    if (hasCallback) {
      myCallbacks[callbackId] = callback
    }
  }

  var __EsenYunJSBridge = {
    // public
    invoke: _call,
    call: _call,
    myCall: _myCall,

    getTemplateContent: getTemplateContent,
    setTemplateContent: setTemplateContent,
    appGetContent: appGetContent,
    appSetContent: appSetContent,
    templateContent: templateContent, // h5 生成的内容
    appContent: appContent, // app 生成的内容
    getAppContent: getAppContent,
    validation: validation,
    _fetchQueue: _fetchQueue,
    dispatchMessageFromNative: _dispatchMessageFromNative,
    registerHandler: registerHandler,
    setTemplateValid: setTemplateValid,
    messageHandlers: messageHandlers,
    handleMessage: _handleMessage
  }

  var doc = document
  _createQueueReadyIframe(doc)
  window.appGetContent = appGetContent
  window.appSetContent = appSetContent
  window.EsenYunJSBridge = __EsenYunJSBridge
})()

// const app =  new Prxoy()
