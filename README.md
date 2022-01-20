# Ethereum 地址生成工具
## 使用方法

``` shell
java -jar -Dp=yourPassword -Dpath=yourKeystorePath -Dq=3 eth-address-generator-jar-with-dependencies.jar
```

### 参数说明
`p`: 必传, 生成walletfile的密码

`path`: 非必传, walletfile存放的路径, 默认为当前目录下的keystore目录

`q`: 非必传, 生成地址的数量, 默认为1, 范围为[1, 100]


