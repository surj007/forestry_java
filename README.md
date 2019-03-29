# forestry_java

## 所需环境
java mysql redis

## 本地运行
将项目导入idea，将application.properties中数据库url、username、password改为当前本地环境，运行

## 服务器部署
Maven Project，Lifecycle，packet生成jar包  
将jar包上传到服务器  
运行nohup java -jar forestry_java.jar &