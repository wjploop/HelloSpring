# 记录一些不易发现的问题

* kotlin 使用注解验证时，需改 `@Email` > `@field:Email`,否则不处理 
* spring valid 国际化时，沿用注解上的 `defaultMessage`，拼凑上注解上的 'field' 对应的国际化的名称