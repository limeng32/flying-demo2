# mybatis.flying 自动映射 pojo 跨库并使用二级缓存 的 demo2

How to play？

1、获取代码（clone or fork），搭建成 maven 项目。

2、以 maven 命令执行 tomcat7:run

3、以下是初始化时的添加的数据源 dataSource 和 dataSource2，将商品业务数据和用户数据分开存放是一种常见做法。dataSource 描述了两个购物车和 12 种商品和商品装入购物车的情况：
```
<dataset>
	<CART ID="1" DEAL="0" DEAL_TIME=null />
	<CART ID="2" DEAL="0" DEAL_TIME=null />
	
	<COMMODITY ID="1" NAME="牙刷A" PRICE="1200" />
	<COMMODITY ID="2" NAME="牙刷B" PRICE="1850" />
	<COMMODITY ID="3" NAME="牙刷C" PRICE="2100" />
	<COMMODITY ID="4" NAME="佳洁士牙膏" PRICE="1499" />
	<COMMODITY ID="5" NAME="六必治牙膏" PRICE="1999" />
	<COMMODITY ID="6" NAME="云南白药牙膏" PRICE="2499" />
	<COMMODITY ID="7" NAME="牙刷A" PRICE="3500" />
	<COMMODITY ID="8" NAME="牙刷B" PRICE="3900" />
	<COMMODITY ID="9" NAME="牙刷C" PRICE="5100" />
	<COMMODITY ID="10" NAME="佳洁士牙膏" PRICE="2800" />
	<COMMODITY ID="11" NAME="六必治牙膏" PRICE="3200" />
	<COMMODITY ID="12" NAME="云南白药牙膏" PRICE="4900" />
	
	<CART_COMMODITY ID="1" CART_ID="1" COMM_ID="1" AMOUNT="3" />
	<CART_COMMODITY ID="2" CART_ID="1" COMM_ID="5" AMOUNT="4" />
	<CART_COMMODITY ID="3" CART_ID="1" COMM_ID="8" AMOUNT="1" />
	<CART_COMMODITY ID="4" CART_ID="1" COMM_ID="12" AMOUNT="1" />
	<CART_COMMODITY ID="5" CART_ID="2" COMM_ID="2" AMOUNT="2" />
	<CART_COMMODITY ID="6" CART_ID="2" COMM_ID="4" AMOUNT="1" />
	<CART_COMMODITY ID="7" CART_ID="2" COMM_ID="9" AMOUNT="2" />
	<CART_COMMODITY ID="8" CART_ID="2" COMM_ID="11" AMOUNT="1" />
</dataset>
```
dataSource2描述了3种会员级别和3位用户的情况：
```
<dataset>
	<ROLE ID="1" NAME="普通会员" VALUE="normal" />
	<ROLE ID="2" NAME="银牌会员" VALUE="silver" />
	<ROLE ID="3" NAME="金牌会员" VALUE="gold" />
	
	<PERSON ID="1" NAME="张三" ROLE_ID="1" />
	<PERSON ID="2" NAME="李四" ROLE_ID="2" />
	<PERSON ID="3" NAME="王五" ROLE_ID="3" />
</dataset>
```
现在，在浏览器中输入以下 url 可以看到效果：

查看购物车：			http://localhost:8080/flying-demo/getCart?id=${购物车cart的id}

查看商品：			http://localhost:8080/flying-demo/getCommodity?id=${商品commodity的id}

增加新商品：			http://localhost:8080/flying-demo/addCommodity?name=${新商品名称}&price=${新商品价格}

编辑商品：			http://localhost:8080/flying-demo/updateCommodity?id=${商品的id}&name=${商品的名称}&price=${商品的价格}

查看购物车中的商品：	http://localhost:8080/flying-demo/getCommodityByCart?id=${购物车的id}

对购物车进行结账：	http://localhost:8080/flying-demo/dealCart?id=${购物车的id}

取消购物车的结账：	http://localhost:8080/flying-demo/undealCart?id=${购物车的id}

查看用户：			http://localhost:8080/flying-demo2/getPerson?id=${用户的id}

查看会员级别：		http://localhost:8080/flying-demo2/getRole?id=${会员级别的id}

编辑会员级别：		http://localhost:8080/flying-demo2/updateRoleDirectly?id=${会员级别的id}&name=${会员级别的名称}

不刷新缓存的编辑会员级别：http://localhost:8080/flying-demo2/updateRoleDirectlyWithoutCache?id=${会员级别的id}&name=${会员级别的名称}

以上方法的实现代码为： https://github.com/limeng32/flying-demo2/blob/master/src/main/java/indi/demo/flying/web/CommonController.java 

以上API方法除最后一个外，其余均使用了二级缓存。您可以调用 `updateRoleDirectlyWithoutCache` 修改会员级别名称，之后调用 `getRole` 能看到新的名称，但调用 `getCart` 和 `getCommodityByCart` 只能看到旧的名称，这是因为 `updateRoleDirectlyWithoutCache` 设计为不支持二级缓存，从这里可以看出缓存确实发挥了作用；如果您调用 `updateRoleDirectly` 修改会员级别名称，`getRole`、`getCart`和 `getCommodityByCart`都会显示出新的名称，因为 `updateRoleDirectly` 设计为支持二级缓存。

最后，`updateRoleDirectly` 和 `updateRoleDirectlyWithoutCache` 都不是 flying 自动映射方法而是普通 mybatis 方法，这个例子也说明改造 mybatis 二级缓存的插件可供 flying 自动映射方法和非 flying 自动映射方法同时工作，如果再使用 redis 托管 mybatis 的二级缓存就成为可扩展的强大缓存解决方案，不过这已超过本例的讨论范围。
