SQL> connect / as SYSDBA
Connected.

SQL> SELECT username, account_status FROM dba_users WHERE ACCOUNT_STATUS LIKE '%EXPIRED%';

SQL> ALTER USER system IDENTIFIED BY system;         
User altered.

SQL> ALTER USER system ACCOUNT UNLOCK;
User altered.

SQL> ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;
Profile altered.

SQL> commit;

SQL> exit