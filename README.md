# server
项目的后台部分
# commit 规范
Git Commit 规范
## 一、Commit 规范的作用
- 提供更多的历史信息，方便快速浏览
- 可以过滤某些commit（比如文档改动），便于快速查找信息
- 可以直接从commit生成Change log

## 二、具体规则
先来看看公式：
<type>(<scope>): <subject>
### 2.1 type
用于说明 ​commit 的类别，只允许使用下面7个标识。
feat：新功能（feature）
fix：修补bug
docs：文档（documentation）
style： 格式（不影响代码运行的变动）
refactor：重构（即不是新增功能，也不是修改bug的代码变动）
test：增加测试
chore：构建过程或辅助工具的变动
### 2.2 scope
用于说明 ​commit 影响的范围，比如数据层、控制层、视图层等等，视项目不同而不同。
### 2.3 subject
是 ​commit 目的的简短描述，不超过50个字符。
1.以动词开头，使用第一人称现在时，比如change，而不是changed或changes
2.第一个字母小写
3.结尾不加句号（.）

规范参考自阮一峰老师的文章：Commit message 和 Change log 编写指南。
