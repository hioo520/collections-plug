**sql+**
1.必须遵循驼峰命名风格(实体类名对应的表的列名)
SqlBean 
>1.如果配置了具体的sql语句对于重复的属性名(或者列名)必须指代具体属于哪个类的(eg. id 如果多个类公用Id 必须配置 类名.id 否则不会查询  也就是对于重复的属性 必须指定否则不予查找)
>2.对于sql+ 填充到对象的方法 只能处理对应列表中数据填充到对应对象中数据