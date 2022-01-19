# Ethereum 地址生成工具
## 使用方法

``` shell
java -jar -Dpassword=yourPassword -Dpath=yourKeystorePath -Dquantity=3 eth-address-generator-jar-with-dependencies.jar
```

### 参数说明
#### password
生成walletfile的password, 必传

#### path
walletfile存放的路径, 默认为当前目录的keystore目录

#### quantity
生成地址的数量, 默认为1, 范围为[1, 100]
