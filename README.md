基于SpringCloud-alibaba的oauth2分布式项目，
使用nacos+gateway+spring security oauth2，认证服务器+资源服务器.也集成了logstash收集日志

Based on the oauth2 distributed project of springcloud-alibaba, 
using nacos+gateway+spring security oauth2, authentication server + resource server.

启动前安装好nacos，nacos做注册中心和配置中心，导入sql数据库。

auth-9999   ：认证服务器
commons-api ：公共类
gateway-3344：网关
provider-9001：资源服务器9001
provider-9002：资源服务器9002

授权码存储模式为jdbc(mysql),之前基于内存。导入项目下的cloud.sql文件到数据库，数据库名称为cloud.
客户端配置存储也使用jdbc(mysql),之前也是基于内存，可以阅读代码还原为内存模式。
令牌生成策略为JWT形式，使用JWT方式可以使资源服务器不需要调用验证服务器验证，增强服务稳定性和可靠性。
密码生成和验证方式为PasswordEncoder方式，有效避免密码安全问题。

1.####----------- 获取token--------------------
http://127.0.0.1:3344/oauth/token 
Authorization:选择Basic Auth,然后填入
    Username:client-1
    Password:secret
在表单中添加：
    grant_type:password
    username:heyong
    password:123456
    scope:ROLE_ADMIN
2.####----------------- 验证token------------------
http://127.0.0.1:3344/oauth/check_token
在表单中添加：
token:xxxxx

3.#### ------------------获取接口数据---------------------
http://127.0.0.1:3344/payment/get/6
Authorization:选择Bearer Token,然后填入
Token:xxxxx

4.###  ------------------插入接口数据--------------------- 
http://127.0.0.1:3344/payment/create
Authorization:选择Bearer Token,然后填入
Token:xxxxx
参数:选择body,选择raw,然后填入
{"id":5,"serial":"CAP 500ML"}
 
5.#### -------------------refreshToken--------------------
post请求，跟请求token一样的url地址：http://localhost:9098/oauth/token
post需要的参数：

grant_type ：refresh_token
client_id：分配的客户端id
client_secret：分配的客户端密码
refresh_token：值为上一次请求返回值中refresh_token的值