# swf4j
轻量级Java Web框架，实现IOC, MVC, AOP特性

* 包结构
    * annotation 注解
        * Action 请求分发 类似@RequestMapping
        * Aspect 切面
        * Controller 控制器
        * Inject 依赖注入 类似@Autowired
        * Service 服务
        * Transaction 事务控制 类似@Transactional
    * bean 框架中使用的到对象
        * Data 数据对象
        * Handler 服务处理对象
        * Param 参数对象
        * Request 服务请求对象
        * View 试图对象
    * helper 助手类
        * AopHelper
        * BeanHelper
        * ClassHelper
        * ConfigHelper 读取用户填写的配置文件
        * ControllerHelper
        * DatabaseHelper 封装JDBC常用操作 如开启、提交、回滚事务
        * IocHelper IOC相关操作
    * proxy 
    * util 工具包
        * ClassUtil 类操作工具类
        * CodecUtil 编码与解码操作工具类
        * JsonUtil 使用Jackson实现POJO的序列化与反序列化
        * PropsUtil 读取配置文件工具类
        * ReflectionUtil 反射工具类
        * StreamUtil 流操作工具类
    * ConfigConstant 配置常量
    * DispatcherServlet 继承HttpServlet 整个框架加载完成后本质上就是一个Servlet
    * HelperLoader 封装各Helper类的加载