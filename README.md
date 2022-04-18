# 图书馆管理系统（library-system）



## 本地快捷预览项目

第一步：运行 sql 文件夹下的`springboot-vue.sql`，创建`springboot-vue`数据库

第二步：双击 run 文件夹下的`start.cmd`，弹出的dos窗口不要关闭

第三步：浏览器访问`localhost:9090`，测试账号需要自己插入到数据库中



## 主要技术

SpringBoot、Mybatis-Plus、MySQL、Vue3、ElementPlus等



## 主要功能

管理员模块：注册、登录、书籍管理、读者管理、借阅管理、借阅状态、修改个人信息、修改密码

读者模块：注册、登录、查询图书信息、借阅和归还图书、查看个人借阅记录、修改个人信息、修改密码



## 主要功能截图

### 登录

登录支持两种用户角色：管理员和读者

输入正确的账号、密码后，系统会自动识别管理员或者用户，并跳转到相应主页。



![](images/login.png)



### 展示板页面

![dashboard](images/dashboard.png)

### 管理员界面

#### 图书管理

- 图书表格列表

   ![book](images/book.png)

- 添加图书

   ![add_book](images/add_book.png)

- 编辑图书

   ![edit_book](images/edit_book.png)

- 删除图书

   ![delete_book](images/delete_book.png)

   



#### 读者管理

- 读者管理的增删查改类似图书管理，不再赘述

  ![](images/reader.png)

  

#### 借阅管理

- 借阅记录查询

  ![](images/lendrecord.png)

- 借阅记录编辑

  ![lendrecord_edit](images/lendrecord_edit.png)

  #### 借阅状态
  
  ![lendstatus](images/lendstatus.png)



### 读者界面

#### 读者信息

- 编辑个人信息

  ![](images/person_edit.png)

- 修改密码

  点击修改密码按钮，修改密码

  ![](images/person_password.png)



#### 图书查询

![](images/book_search.png)

#### 借阅图书

![](images/lendbook.png)

![](images/lendbook_2.png)

#### 归还图书

![](images/returnbook.png)

#### 借阅记录

![](images/book_information.png)

#### 借阅状态

![book_status2](images/book_status2.png)



## 代码结构

### 前端

```shell
library-ui
├─api			// api接口
├─assets		// 资源文件	
│  ├─icon	 	// 图标
│  ├─img	 	// 图片
│  └─styles	 	// 样式
├─components	// 自定义Vue组件
├─layout		// 页面布局
├─router		// Vue路由
├─utils			// 工具函数
└─views			// 页面
```

![](images/ui.png)



### 后端

maven项目结构

```shell
library-serve
├─java
│  └─com
│      └─admin
│          └─library
│              ├─common			// 通用类
│              │  ├─base		// 基础类
│              │  └─config		// 配置类
│              ├─controller		// 控制层
│              ├─domain			// 实体类
│              ├─mapper			// 持久层
│              └─service		// 业务层
└─resources	// maven资源配置
```

![](images/application.png)



## 数据库

**使用Navicat生成ER模型图**

![](images/sql.png)
