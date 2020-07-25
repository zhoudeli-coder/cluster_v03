# 实现了公共模块依赖引用和jpa实现数据库增删改查
- 依赖添加
~~~
    <!--公共的依赖-->
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>cluster-common</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <version>2.2.0.RELEASE</version>
    </dependency>
    <!--Mysql 驱动包-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
~~~

- 注解支持
~~~
    @EnableJpaRepositories(basePackages = "com.example")
    @EntityScan("com.example.common.entity")
~~~

- 接口实现
~~~
    public interface UserRepository extends JpaRepository<User, Integer> {
    }
~~~

- JpaRepository 方法调用
~~~
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public String add(User user){
        userRepository.save(user);
        return "添加成功" + user.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return "删除成功";
    }

    @PutMapping("/")
    public String update(User user) {
        userRepository.save(user);
        return "修改成功";
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        return userRepository.getOne(id);
    }

    @GetMapping("/list")
    public List<User> list(){
        List<User> userList = userRepository.findAll();
        return userList;
    }
~~~

# 实现了RestTemplate 调用cerfification（验证微服务）服务
- 注入RestTemplate Bean
~~~
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
~~~

- restTemplate 服务调用
~~~
    private static final String REST_URL_PREFIX = "http://localhost:3001/certification/users";
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public String add(User user) {
        try {
            restTemplate.postForObject(REST_URL_PREFIX + "/", user, String.class);
        } catch (Exception e) {
            return String.format("添加失败[%s]", e.getMessage());
        }
        return String.format("添加成功[%s]", user.getId());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            restTemplate.delete(String.format(REST_URL_PREFIX + "/%s", id), String.class);
        } catch (Exception e) {
            return String.format("删除失败[%s]", e.getMessage());
        }
        return "删除成功";
    }

    @PutMapping("/")
    public String update(User user) {
        try {
            restTemplate.put(REST_URL_PREFIX, user, String.class);
        } catch (Exception e) {
            return String.format("修改失败[%s]", e.getMessage());
        }
        return "修改成功";
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        try {
            return restTemplate.getForObject(String.format(REST_URL_PREFIX + "/%s", id), User.class);
        } catch (Exception e) {
            return new User(String.format("修改失败[%s]", e.getMessage()), "");
        }
    }
~~~

# cluster-product 集成 mybatis实现增删改查

- 添加依赖
~~~
    <!--导入 mybatis 启动器-->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.3</version>
    </dependency>
~~~

- 注解方式实现增删改查
~~~
    @Mapper //或者用@MapperScan(basePackages = "com.example.product.mapper")
    public interface ProviderMapper {
    
        //useGeneratedKeys是否使用自增主键，keyProperty指定实体类中的哪一个属性封装主键值
        @Options(useGeneratedKeys = true, keyProperty = "id")
        @Insert("insert into provider(p_name) values(#{name})")
        int add(Provider provider);
    
        @Delete("delete from provider where p_id=#{id}")
        int delete(Integer id);
    
        @Update("update provider set p_name=#{name} where p_id=#{id}")
        int update(Provider provider);
    
        @Select("select p_id id, p_name name from provider where p_id=#{id}")
        Provider get(Integer id);
    }
~~~

- 配置文件方式实现增删改查