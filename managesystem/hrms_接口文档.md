# 返回报文
返回报文的统一格式封装为一下内容
```
{"code":0,"message":"", 其他数据}
code 0 表示操作成功
code 1 表示操作失败， 操作失败时 messag 内容代表操作失败的提示信息

其他需要返回的数据 字段没有使用统一的属性名进行封装，返回的数据会有不同
例如 {"code":0,"message":"","username":"admin"}
或
{"code":0,"message":"","rows":[{"id":15,"name":"人事部","description":"123"},{"id":19,"name":"123","description":"123"},{"id":20,"name":"12345","description":"123"}], "total":3}

可以使用Map<String,Object> 保存所有的返回结果，然后序列化为json

例如 ，以下为伪代码

Map<String,Object> map= new HashMap<>();
map.put("code", 0);
map.put("message", "");
map.put("rows", List<XX>);  // xx用来封装数组中属性的对象，例如需要封装 {"id":15,"name":"人事部","description":"123"} 这种部门对象
map.put("total",3);

String data = toJsonString(map);
```

# 分页

展示页面下方会展示分页栏

![image-20201017092700950](image/image-20201017092700950.png)

假设部门信息有19条，网页上并不会一次性展示全部，而是显示指定条数据（图中为10条），因此服务器在返回数据的时候只需要返回指定条数的数据即可。
前台会发送两个数字来指导服务器查询， page 和 rows， page表示当前页编号从1起， rows表示每页显示的行数。例如 {"page":2 , "rows": 5}，表示需要展示的数据为第二页，展示5条数据。服务器需要返回指定条数的数据，并且需要返回数据的总条数，用来指导前台代码计算一共有多少页。



![分页](image/分页.png)


# 部门模块

## 部门列表查询

```
路径: /dept/list
请求数据: {name: "1", page: 1, rows: 10}
数据描述：page 当前的页数
        rows 每页的条数
        name为可选数据，用于检索，可以为空字符串，也可以不传
        
返回数据
操作成功：{"total":3,"code":0,"rows":[{"name":"人事部","id":1,"description":"管理人事"},{"name":"研发部","id":2,"description":"管理研发"},{"name":"产品部","id":3,"description":"管理产品"}],"message":""}
        total: 总的记录条数
        code：0 表示操作成功
        rows：展示记录的列表，是一个对象集合
            rows里面的每一个元素 {"name":"人事部","id":1,"description":"管理人事"}
                name: 部门名称
                id：唯一标识
                description: 部门描述
操作失败：{"code":1,"message":"服务器异常"}
        code: 1表示失败
        message： 表示失败的消息
```

## 部门信息添加

```
路径: /dept/add
请求数据: {id: "", name: "123", description: "123"}

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"部门名称重复"}
        code: 1表示失败
        message： 表示失败的消息
```

## 部门信息修改

修改信息的时候，开启修改窗口时，不需要从后台加载数据，直接从页面上获取指定部门的信息填写到了表格中。因此没有查询单个部门信息的接口。

```
路径: /dept/update
请求数据: {id: "2", name: "123", description: "123"}

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"部门名称重复"}
        code: 1表示失败
        message： 表示失败的消息
```

## 部门删除

```
路径: /dept/delete
请求数据: {"ids":[19,20]}
				可以删除多条记录，删除的时候需要保证没有员工关联这条部门数据

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"删除失败"}
        code: 1表示失败
        message： 表示失败的消息
```

## 员工下拉列表查询部门

用户员工模块查询部门列表

```
路径: /dept/getcombobox
请求数据: 无

返回数据
操作成功：[{"name":"人事部","id":1,"description":"管理人事"},{"name":"研发部","id":2,"description":"管理研发"},{"name":"产品部","id":3,"description":"管理产品"}]
        name: 部门名称
        id：唯一标识
        description: 部门描述
操作失败：不返回数据
```

# 职位模块

职位的操作和部门的操作非常类似

## 职位列表查询

```
路径: /position/list
请求数据: {name: "1", page: 1, rows: 10}
数据描述：page 当前的页数
        rows 每页的条数
        name为可选数据，用于检索，可以为空字符串，也可以不传

返回数据
操作成功：{"total":3,"code":0,"rows":[{"name":"员工","id":1,"description":"职员"},{"name":"组长","id":2,"description":"管理职员"},{"name":"部长","id":3,"description":"管理组长"}],"message":""}
        total: 总的记录条数
        code：0 表示操作成功
        rows：展示记录的列表，是一个对象集合
            rows里面的每一个元素 {"name":"员工","id":1,"description":"职员"}
                name: 置为名称
                id：唯一标识
                description: 职位描述
操作失败：{"code":1,"message":"服务器异常"}
        code: 1表示失败
        message： 表示失败的消息
```

## 职位信息添加

添加修改共用一个接口，添加的时候不传id参数，修改的时候，需要在url后面传id参数

```
路径: /position/add
请求数据: {id: "", name: "123", description: "123"}

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"部门名称重复"}
        code: 1表示失败
        message： 表示失败的消息
```

## 职位信息修改

添加修改共用一个接口，添加的时候不传id参数，修改的时候，需要在url后面传id参数

```
路径: /position/save
请求数据: {id: "1", name: "123", description: "123"}

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"部门名称重复"}
        code: 1表示失败
        message： 表示失败的消息
```

## 职位删除

```
路径: /position/delete
请求数据: {"ids":[19,20]}
				可以删除多条记录，删除的时候需要保证没有员工关联这条部门数据

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"删除失败"}
        code: 1表示失败
        message： 表示失败的消息
```

## 员工下拉列表查询职位

用户员工模块查询职位列表

```
路径: /position/getcombobox
请求数据: 无

返回数据
操作成功：[{"id":2,"name":"经理","description":"1234"},{"id":3,"name":"员工","description":"1"},{"id":4,"name":"职员","description":"123"},{"id":5,"name":"组长","description":"123"}]
        name: 职位名称
        id：唯一标识
        description: 职位描述
操作失败：不回复数据
```

# 员工模块


## 员工列表查询

```
路径: /empl/list
请求数据: {id: "1", name: "", departmentName: "", positionName: "", sex: "", page: 1, rows: 10}
数据描述：page 当前的页数
        rows 每页的条数
        其中 page 和 row 是必传字段
        其余都是可选，可以不传，也可传空字符，根据相应条件进行检索
        
返回数据
操作成功：{"total":1,"code":0,"rows":[{"id":"user1","name":"e1","sex":"male","phone":"13312345678","email":"133@163.com","address":"地址1","education":"Master","birthday":"1990-10-10","departmentId":2,"departmentName":"研发部","positionId":1,"positionName":"员工"}],"message":""}
        total: 总的记录条数
        code：0 表示操作成功
        rows：展示记录的列表，是一个对象集合
            rows里面的每一个元素 {"id":"user1","name":"e1","sex":"male","phone":"13312345678","email":"133@163.com","address":"地址1","education":"Master","birthday":"1990-10-10","departmentId":2,"departmentName":"研发部","positionId":1,"positionName":"员工"}
                name: 员工名称
                id：员工编号
                sex：性别  male 或 female
                phone: 手机
                email 邮箱
                address 住址
                education  Bachelor 或 Master 或 Doctor
                birthday 年-月-日字符串
                departmentId 部门id
                departmentName 部门名称
                positionId  职位id
                positionName 职位名称
操作失败：{"code":1,"message":"服务器异常"}
        code: 1表示失败
        message： 表示失败的消息
```

## 员工信息添加

员工不同于职位和部门，职位和部门由服务器生成id，员工需要用户在页面上自己指定id传入到服务器，需要保证指定的id是唯一的，不能和已有数据冲突

```
路径: /empl/add

请求数据:{"id":"9527","name":"张三","sex":"male","phone":"13312345678","email":"test@163.com","address":"玄武区","education":"Bachelor","birthday":"2020-10-17","departmentId":"15","positionId":"2"}
        id 员工的唯一标识，为用户自行填写而不是系统生成，需要保证唯一性
        name 员工姓名
        sex  male 或 female
        phone 电话
        email 邮箱
        address 住址
        education  Bachelor 或 Master 或 Doctor
        birthday 年-月-日字符串
        departmentId 部门id
        positionId  职位id

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"员工id冲突"}
        code: 1表示失败
        message： 表示失败的消息
        添加员工：如果已有存在的id，返回操作失败，并给与提示信息
```

## 员工信息修改

```
路径: /empl/update

请求数据:{"id":"9527","name":"张三","sex":"male","phone":"13312345678","email":"test@163.com","address":"玄武区","education":"Bachelor","birthday":"2020-10-17","departmentId":"15","positionId":"2"}
        id 员工的唯一标识，根据id修改指定员工的信息
        name 员工姓名
        sex  male 或 female
        phone 电话
        email 邮箱
        address 住址
        education  Bachelor 或 Master 或 Doctor
        birthday 年-月-日字符串
        departmentId 部门id
        positionId  职位id

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"员工id不存在"}
        code: 1表示失败
        message： 表示失败的消息
```

## 员工删除

```
路径: /empl/delete
请求数据: {"ids":[19,20]}

操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"删除失败"}
        code: 1表示失败
        message： 表示失败的消息
```

# 管理员模块

用户登录使用的账号和密码是管理员模块添加的信息，而不是员工模块的账号

id 为 1 的管理员为超级管理员，超级管理员的信息不能被修改和删除

前端对超级管理员进行了校验，选中超级管理员点击修改或删除按钮将提示错误


## 登录
```
路径：/admin/login
请求数据: 
{"username":"admin","password":"123456"}

返回数据：
登录成功
{"code":0,"message":"操作成功"}

登录失败
{"code":1,"message":"用户名或密码错误"}
```

## 管理员列表查询
```
路径: /admin/list
请求数据: {username: "1", page: 1, rows: 10}
数据描述：page 当前的页数
        rows 每页的条数
        username为可选数据，用于检索，可以为空字符串，也可以不传

返回数据
操作成功：{"total":1,"code":0,"rows":[{"id":1,"username":"u1","rolename":"admin","password":"123456"},{"id":2,"username":"u2","rolename":"admin","password":"123456"}],"message":""}
        total: 总的记录条数
        code：0 表示操作成功
        rows：展示记录的列表，是一个对象集合
            rows里面的每一个元素 {"id":1,"username":"u1","rolename":"admin","password":"123456"}
                username: 系统的登录用户名
                id：唯一标识， 为1表示超级管理员
                rolename: 角色，没有实际的作用，固定为 admin
                password: 密码
操作失败：{"code":1,"message":"服务器异常"}
        code: 1表示失败
        message： 表示失败的消息
```

## 管理员信息添加
```
路径: /admin/add
请求数据: {id: "", username: "1111", password: "1111"}

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"部门名称重复"}
        code: 1表示失败
        message： 表示失败的消息
```

## 管理员信息修改

```
路径: /admin/update
请求数据: {id: "60", username: "4444", password: "4444"}

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"部门名称重复"}
        code: 1表示失败
        message： 表示失败的消息
```

## 管理员删除

```
路径: /admin/delete
请求数据: {"ids":[19,20]}

返回数据
操作成功：{"code":0,"message":""}
        code：0 表示操作成功
操作失败：{"code":1,"message":"删除失败"}
        code: 1表示失败
        message： 表示失败的消息
```

## 管理员信息查询
用户右上角的名称展示
```
路径: /admin/info
请求数据: 无

返回数据
操作成功：{"code":0,"message":"","username":"u1"}
        username： 用户展示信息
        code：0 表示操作成功
操作失败：{"code":1,"message":"操作失败"}
        code: 1表示失败
        message： 表示失败的消息

```


## 管理员退出
```
路径: /admin/logout
请求数据: 无

操作成功：{"code":0,"message":"","username":"u1"}
        username： 用户展示信息
        code：0 表示操作成功
操作失败：{"code":1,"message":"操作失败"}
        code: 1表示失败
        message： 表示失败的消息
```


