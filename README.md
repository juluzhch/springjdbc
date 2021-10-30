# srpingjdbc

验证spring 事务的逻辑
server->dao
//service: 事务管理器，dao
//dataSource事务管理-需要datasource （使用哪种事务管理器，需要与dao层使用的持久化框架一致，比如srping jdbc ，mybatis 等基于datasource 的用DataSourceTransactionManager
//dao ->jdbcTemplate
