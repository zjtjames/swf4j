# swf4j
轻量级Java Web框架，实现IOC, MVC, AOP特性

* 包结构
    * annotation 注解
        * Action 请求分发 类似RequestMapping
        * Aspect 切面类 
        * Controller 控制器
        * Inject 依赖注入 类似Autowired
        * Service 服务
        * Transaction 事务控制 类似Transactional
    * bean 
    * helper 
    * proxy 
    * util 工具包
    * ConfigConstant 配置常量
    * DispatcherServlet 继承HttpServlet 整个框架加载完成后本质上就是一个Servlet
    * HelperLoader 封装各Helper类的加载