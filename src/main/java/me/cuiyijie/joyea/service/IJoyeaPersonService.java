package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.JoyeaPerson;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IJoyeaPersonService {

    List<JoyeaPerson> list(JoyeaPerson joyeaPerson);

}