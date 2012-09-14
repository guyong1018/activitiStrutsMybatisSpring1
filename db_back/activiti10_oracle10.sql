------------------------------------------
-- Export file for user ACTIVITI10      --
-- Created by hw on 2012/9/13, 15:44:37 --
------------------------------------------

spool activiti10.log

prompt
prompt Creating sequence SEQ_FORM
prompt ==========================
prompt
create sequence ACTIVITI10.SEQ_FORM
minvalue 1
maxvalue 1000000000000000000
start with 144
increment by 1
cache 10;

prompt
prompt Creating sequence SEQ_QJD
prompt =========================
prompt
create sequence ACTIVITI10.SEQ_QJD
minvalue 1
maxvalue 1000000000000000000
start with 78
increment by 1
cache 10;

prompt
prompt Creating sequence SEQ_QJGC
prompt ==========================
prompt
create sequence ACTIVITI10.SEQ_QJGC
minvalue 1
maxvalue 1000000000000000000
start with 120
increment by 1
cache 10;


spool off
