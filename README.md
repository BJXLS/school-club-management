# 社团管理系统

## 相关技术框架

+ layui
+ beetl
+ springboot
+ shiro
+ mybatis-plus
+ druid

## 用户角色和相关功能

### 管理员

+ 功能
  + 系统管理
    + 用户管理：对用户的增删改查，excel批量导入用户，封禁用户
    + 角色管理：对角色的增删改查，对角色进行权限更改（shrio可以精确到单独的函数权限）
    + 菜单管理：对菜单的增删改查
  + 日志管理
    + 登录日志的查看
    + 操作日志的查看

### 社团管理员

+ 功能
  + 管理社团
    + 运营社团
      + 对社团信息进行查看：社团id，名称，类型，成员人数，状态（是否封禁），社长等
    + 审核社团
      + 对社团成立进行审核
  + 管理活动
    + 运营活动
      + 查看活动信息：所属社团id，活动名称，活动icon，活动类型，状态，时间，详情
    + 审核活动
      + 对活动进行审核：同意，驳回（并且输入驳回意见）

### 普通学生

+ 功能
  + 社团大厅
    + 查看现有社团信息
    + 对感兴趣的社团进行报名
    + 创建社团建立申请
  + 活动大厅
    + 查看活动
    + 报名活动
  + 消息大厅
    + 我的申请：查看申请信息状态
    + 我的审核：查看待审核状态
  + 我的社团
    + 创建的社团：查看社团信息，社团人员管理，社团活动查看和创建申请
    + 参加的社团：查看社团信息
  + 我的活动
    + 创建的活动：查看活动信息
    + 参加的活动：查看活动信息

### 注册登录功能

+ 实现登录和注册功能
+ 实现邮箱发送验证码，重置密码功能

## 运行图片

+ 登录

![image-20220126125753490](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126125753490.png)

![image-20220126125811940](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126125811940.png)

+ 社团活动相关

![image-20220126124035443](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126124035443.png)

![image-20220126124057138](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126124057138.png)

![image-20220126124232997](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126124232997.png)

![image-20220126124423758](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126124423758.png)

![image-20220126124651646](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126124651646.png)

![image-20220126124706905](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126124706905.png)

![image-20220126124724932](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126124724932.png)

+ 管理员相关

![image-20220126133020905](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126133020905.png)

![image-20220126133041233](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126133041233.png)

![image-20220126133052918](C:\Users\hi\AppData\Roaming\Typora\typora-user-images\image-20220126133052918.png)



