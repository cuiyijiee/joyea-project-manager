SELECT a.dfsname, COUNT(b.dfsid) AS instnum
FROM tbl_dfslist a
         LEFT JOIN tbl_instance b ON a.id = b.dfsid
GROUP BY a.id;