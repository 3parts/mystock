/*
SQLyog v10.2 
MySQL - 5.7.10-enterprise-commercial-advanced-log : Database - mystock
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mystock` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mystock`;

/*Table structure for table `bsd` */

DROP TABLE IF EXISTS `bsd`;

CREATE TABLE `bsd` (
  `djid` varchar(14) NOT NULL,
  `riqi` datetime NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`djid`),
  KEY `FK17E33532696FE` (`userid`),
  CONSTRAINT `FK17E33532696FE` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bsd` */

/*Table structure for table `bsdsp` */

DROP TABLE IF EXISTS `bsdsp`;

CREATE TABLE `bsdsp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `djid` varchar(14) NOT NULL,
  `spid` varchar(10) NOT NULL,
  `spname` varchar(20) DEFAULT NULL,
  `spdw` varchar(20) DEFAULT NULL,
  `spxinghao` varchar(20) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `zj` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bsdsp` */

/*Table structure for table `byd` */

DROP TABLE IF EXISTS `byd`;

CREATE TABLE `byd` (
  `djid` varchar(14) NOT NULL,
  `riqi` datetime NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`djid`),
  KEY `FK17EED532696FE` (`userid`),
  CONSTRAINT `FK17EED532696FE` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `byd` */

/*Table structure for table `bydsp` */

DROP TABLE IF EXISTS `bydsp`;

CREATE TABLE `bydsp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `djid` varchar(14) NOT NULL,
  `spid` varchar(10) NOT NULL,
  `spname` varchar(20) DEFAULT NULL,
  `spdw` varchar(20) DEFAULT NULL,
  `spxinghao` varchar(20) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `zj` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bydsp` */

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityname` varchar(30) NOT NULL,
  `proid` int(11) NOT NULL,
  `state` varchar(1) DEFAULT NULL COMMENT '0 - 禁用 1 - 启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1479 DEFAULT CHARSET=utf8;

/*Data for the table `city` */

insert  into `city`(`id`,`cityname`,`proid`,`state`) values (600,'美国',1,'1'),(601,'日本',1,'1'),(602,'英国',1,'1'),(603,'法国',1,'1'),(604,'德国',1,'1'),(605,'加拿大',1,'1'),(606,'澳大利亚',1,'1'),(607,'俄罗斯',1,'1'),(608,'新西兰',1,'1'),(609,'意大利',1,'1'),(610,'韩国',1,'1'),(611,'比利时',1,'1'),(612,'瑞士',1,'1'),(613,'新加坡',1,'1'),(614,'墨西哥',1,'1'),(615,'荷兰',1,'1'),(616,'巴西',1,'1'),(617,'印度',1,'1'),(618,'爱尔兰',1,'1'),(619,'马来西亚',1,'1'),(620,'丹麦',1,'1'),(621,'南非',1,'1'),(622,'西班牙',1,'1'),(1000,'东城区',10,'1'),(1001,'西城区',10,'1'),(1002,'崇文区',10,'1'),(1003,'宣武区',10,'1'),(1004,'朝阳区 ',10,'1'),(1005,'丰台区',10,'1'),(1006,'石景山区',10,'1'),(1007,'海淀区',10,'1'),(1008,'门头沟区',10,'1'),(1009,'房山区',10,'1'),(1010,'通州区',10,'1'),(1011,'顺义区 ',10,'1'),(1012,'昌平区',10,'1'),(1013,'大兴区',10,'1'),(1014,'怀柔区',10,'1'),(1015,'平谷区',10,'1'),(1016,'密云县',10,'1'),(1017,'延庆县',10,'1'),(1018,'黄浦区 ',11,'1'),(1019,'卢湾区',11,'1'),(1020,'徐汇区',11,'1'),(1021,'长宁区',11,'1'),(1022,'静安区',11,'1'),(1023,'普陀区',11,'1'),(1024,'闸北区',11,'1'),(1025,'虹口区 ',11,'1'),(1026,'杨浦区',11,'1'),(1027,'闵行区',11,'1'),(1028,'宝山区',11,'1'),(1029,'嘉定区',11,'1'),(1030,'浦东新区',11,'1'),(1031,'金山区',11,'1'),(1032,'松江区 ',11,'1'),(1033,'青浦区',11,'1'),(1034,'南汇区',11,'1'),(1035,'奉贤区',11,'1'),(1036,'崇明县',11,'1'),(1037,'和平区',12,'1'),(1038,'河东区',12,'1'),(1039,'河西区 ',12,'1'),(1040,'南开区',12,'1'),(1041,'河北区',12,'1'),(1042,'红桥区',12,'1'),(1043,'塘沽区',12,'1'),(1044,'汉沽区',12,'1'),(1045,'大港区',12,'1'),(1046,'东丽区 ',12,'1'),(1047,'西青区',12,'1'),(1048,'津南区',12,'1'),(1049,'北辰区',12,'1'),(1050,'武清区',12,'1'),(1051,'宝坻区',12,'1'),(1052,'宁河县',12,'1'),(1053,'静海县 ',12,'1'),(1054,'蓟县',12,'1'),(1055,'万州区',13,'1'),(1056,'涪陵区',13,'1'),(1057,'渝中区',13,'1'),(1058,'大渡口区',13,'1'),(1059,'江北区',13,'1'),(1060,'沙坪坝区 ',13,'1'),(1061,'九龙坡区',13,'1'),(1062,'南岸区',13,'1'),(1063,'北碚区',13,'1'),(1064,'万盛区',13,'1'),(1065,'双桥区',13,'1'),(1066,'渝北区',13,'1'),(1067,'巴南区 ',13,'1'),(1068,'黔江区',13,'1'),(1069,'长寿区',13,'1'),(1070,'綦江县',13,'1'),(1071,'潼南县',13,'1'),(1072,'铜梁县',13,'1'),(1073,'大足县',13,'1'),(1074,'荣昌县 ',13,'1'),(1075,'璧山县',13,'1'),(1076,'梁平县',13,'1'),(1077,'城口县',13,'1'),(1078,'丰都县',13,'1'),(1079,'垫江县',13,'1'),(1080,'武隆县',13,'1'),(1081,'忠县 ',13,'1'),(1082,'开县',13,'1'),(1083,'云阳县',13,'1'),(1084,'奉节县',13,'1'),(1085,'巫山县',13,'1'),(1086,'巫溪县',13,'1'),(1087,'石柱土家族自治县',13,'1'),(1088,' 秀山土家族苗族自治县',13,'1'),(1089,'酉阳土家族苗族自治县',13,'1'),(1090,'彭水苗族土家族自治县 ',13,'1'),(1091,'江津市',13,'1'),(1092,'合川市',13,'1'),(1093,'永川市',13,'1'),(1094,'南川市',13,'1'),(1095,'石家庄',14,'1'),(1096,'唐山',14,'1'),(1097,'秦皇岛 ',14,'1'),(1098,'邯郸',14,'1'),(1099,'邢台',14,'1'),(1100,'保定',14,'1'),(1101,'张家口',14,'1'),(1102,'承德',14,'1'),(1103,'沧州',14,'1'),(1104,'廊坊 ',14,'1'),(1105,'衡水',14,'1'),(1106,'太原',15,'1'),(1107,'大同',15,'1'),(1108,'阳泉',15,'1'),(1109,'长治',15,'1'),(1110,'晋城',15,'1'),(1111,'朔州 ',15,'1'),(1112,'晋中',15,'1'),(1113,'运城',15,'1'),(1114,'忻州',15,'1'),(1115,'临汾',15,'1'),(1116,'吕梁',15,'1'),(1117,'呼和浩特',16,'1'),(1118,'包头 ',16,'1'),(1119,'乌海',16,'1'),(1120,'赤峰',16,'1'),(1121,'通辽',16,'1'),(1122,'鄂尔多斯',16,'1'),(1123,'呼伦贝尔',16,'1'),(1124,'乌兰察布',16,'1'),(1125,'锡林郭勒盟',16,'1'),(1126,'巴彦淖尔',16,'1'),(1127,'阿拉善盟',16,'1'),(1128,'兴安盟 ',16,'1'),(1129,'沈阳',17,'1'),(1130,'大连',17,'1'),(1131,'鞍山',17,'1'),(1132,'抚顺',17,'1'),(1133,'本溪',17,'1'),(1134,'丹东',17,'1'),(1135,'锦州 ',17,'1'),(1136,'葫芦岛',17,'1'),(1137,'营口',17,'1'),(1138,'盘锦',17,'1'),(1139,'阜新',17,'1'),(1140,'辽阳',17,'1'),(1141,'铁岭',17,'1'),(1142,'朝阳 ',17,'1'),(1143,'长春',18,'1'),(1144,'吉林',18,'1'),(1145,'四平',18,'1'),(1146,'辽源',18,'1'),(1147,'通化',18,'1'),(1148,'白山',18,'1'),(1149,'松原 ',18,'1'),(1150,'白城',18,'1'),(1151,'延边朝鲜',18,'1'),(1152,'哈尔滨',19,'1'),(1153,'齐齐哈尔',19,'1'),(1154,'鹤岗',19,'1'),(1155,'双鸭山',19,'1'),(1156,'鸡西 ',19,'1'),(1157,'大庆',19,'1'),(1158,'伊春',19,'1'),(1159,'牡丹江',19,'1'),(1160,'佳木斯',19,'1'),(1161,'七台河',19,'1'),(1162,'黑河',19,'1'),(1163,'绥化 ',19,'1'),(1164,'大兴安岭',19,'1'),(1165,'南京',20,'1'),(1166,'无锡',20,'1'),(1167,'徐州',20,'1'),(1168,'常州',20,'1'),(1169,'苏州',20,'1'),(1170,'南通 ',20,'1'),(1171,'连云港',20,'1'),(1172,'淮安',20,'1'),(1173,'盐城',20,'1'),(1174,'扬州',20,'1'),(1175,'镇江',20,'1'),(1176,'泰州',20,'1'),(1177,'宿迁 ',20,'1'),(1178,'杭州',21,'1'),(1179,'宁波',21,'1'),(1180,'温州',21,'1'),(1181,'嘉兴',21,'1'),(1182,'湖州',21,'1'),(1183,'绍兴',21,'1'),(1184,'金华 ',21,'1'),(1185,'衢州',21,'1'),(1186,'舟山',21,'1'),(1187,'台州',21,'1'),(1188,'丽水',21,'1'),(1189,'合肥',22,'1'),(1190,'芜湖',22,'1'),(1191,'蚌埠 ',22,'1'),(1192,'淮南',22,'1'),(1193,'马鞍山',22,'1'),(1194,'淮北',22,'1'),(1195,'铜陵',22,'1'),(1196,'安庆',22,'1'),(1197,'黄山',22,'1'),(1198,'滁州 ',22,'1'),(1199,'阜阳',22,'1'),(1200,'宿州',22,'1'),(1201,'巢湖',22,'1'),(1202,'六安',22,'1'),(1203,'亳州',22,'1'),(1204,'池州',22,'1'),(1205,'宣城 ',22,'1'),(1206,'福州',23,'1'),(1207,'厦门',23,'1'),(1208,'莆田',23,'1'),(1209,'三明',23,'1'),(1210,'泉州',23,'1'),(1211,'漳州',23,'1'),(1212,'南平 ',23,'1'),(1213,'龙岩',23,'1'),(1214,'宁德',23,'1'),(1215,'南昌',24,'1'),(1216,'景德镇',24,'1'),(1217,'萍乡',24,'1'),(1218,'新余',24,'1'),(1219,'九江 ',24,'1'),(1220,'鹰潭',24,'1'),(1221,'赣州',24,'1'),(1222,'吉安',24,'1'),(1223,'宜春',24,'1'),(1224,'抚州',24,'1'),(1225,'上饶',24,'1'),(1226,'济南 ',25,'1'),(1227,'青岛',25,'1'),(1228,'淄博',25,'1'),(1229,'枣庄',25,'1'),(1230,'东营',25,'1'),(1231,'潍坊',25,'1'),(1232,'烟台',25,'1'),(1233,'威海 ',25,'1'),(1234,'济宁',25,'1'),(1235,'泰安',25,'1'),(1236,'日照',25,'1'),(1237,'莱芜',25,'1'),(1238,'德州',25,'1'),(1239,'临沂',25,'1'),(1240,'聊城 ',25,'1'),(1241,'滨州',25,'1'),(1242,'菏泽',25,'1'),(1243,'郑州',26,'1'),(1244,'开封',26,'1'),(1245,'洛阳',26,'1'),(1246,'平顶山',26,'1'),(1247,'焦作 ',26,'1'),(1248,'鹤壁',26,'1'),(1249,'新乡',26,'1'),(1250,'安阳',26,'1'),(1251,'濮阳',26,'1'),(1252,'许昌',26,'1'),(1253,'漯河',26,'1'),(1254,'三门峡 ',26,'1'),(1255,'南阳',26,'1'),(1256,'商丘',26,'1'),(1257,'信阳',26,'1'),(1258,'周口',26,'1'),(1259,'驻马店',26,'1'),(1260,'济源',26,'1'),(1261,'武汉 ',27,'1'),(1262,'黄石',27,'1'),(1263,'襄樊',27,'1'),(1264,'十堰',27,'1'),(1265,'荆州',27,'1'),(1266,'宜昌',27,'1'),(1267,'荆门',27,'1'),(1268,'鄂州 ',27,'1'),(1269,'孝感',27,'1'),(1270,'黄冈',27,'1'),(1271,'咸宁',27,'1'),(1272,'随州',27,'1'),(1273,'仙桃',27,'1'),(1274,'天门',27,'1'),(1275,'潜江 ',27,'1'),(1276,'神农架',27,'1'),(1277,'恩施土家',27,'1'),(1278,'长沙',28,'1'),(1279,'株洲',28,'1'),(1280,'湘潭',28,'1'),(1281,'衡阳',28,'1'),(1282,'邵阳 ',28,'1'),(1283,'岳阳',28,'1'),(1284,'常德',28,'1'),(1285,'张家界',28,'1'),(1286,'益阳',28,'1'),(1287,'郴州',28,'1'),(1288,'怀化',28,'1'),(1289,'娄底 ',28,'1'),(1290,'湘西土家',28,'1'),(1291,'永州',28,'1'),(1292,'广州',29,'1'),(1293,'深圳',29,'1'),(1294,'珠海',29,'1'),(1295,'汕头',29,'1'),(1296,'韶关 ',29,'1'),(1297,'佛山',29,'1'),(1298,'江门',29,'1'),(1299,'湛江',29,'1'),(1300,'茂名',29,'1'),(1301,'肇庆',29,'1'),(1302,'惠州',29,'1'),(1303,'梅州 ',29,'1'),(1304,'汕尾',29,'1'),(1305,'河源',29,'1'),(1306,'阳江',29,'1'),(1307,'清远',29,'1'),(1308,'东莞',29,'1'),(1309,'中山',29,'1'),(1310,'潮州 ',29,'1'),(1311,'揭阳',29,'1'),(1312,'云浮',29,'1'),(1313,'南宁',30,'1'),(1314,'柳州',30,'1'),(1315,'桂林',30,'1'),(1316,'梧州',30,'1'),(1317,'北海 ',30,'1'),(1318,'防城港',30,'1'),(1319,'钦州',30,'1'),(1320,'贵港',30,'1'),(1321,'玉林',30,'1'),(1322,'百色',30,'1'),(1323,'贺州',30,'1'),(1324,'河池 ',30,'1'),(1325,'来宾',30,'1'),(1326,'崇左',30,'1'),(1327,'海口',31,'1'),(1328,'三亚',31,'1'),(1329,'五指山',31,'1'),(1330,'琼海',31,'1'),(1331,'儋州 ',31,'1'),(1332,'文昌',31,'1'),(1333,'万宁',31,'1'),(1334,'东方',31,'1'),(1335,'澄迈',31,'1'),(1336,'定安',31,'1'),(1337,'屯昌',31,'1'),(1338,'临高 ',31,'1'),(1339,'白沙黎族',31,'1'),(1340,'江黎族自',31,'1'),(1341,'乐东黎族 ',31,'1'),(1342,'陵水黎族',31,'1'),(1343,'保亭黎族',31,'1'),(1344,'琼中黎族 ',31,'1'),(1345,'西沙群岛',31,'1'),(1346,'南沙群岛',31,'1'),(1347,'中沙群岛 ',31,'1'),(1348,'成都',32,'1'),(1349,'自贡',32,'1'),(1350,'攀枝花',32,'1'),(1351,'泸州',32,'1'),(1352,'德阳',32,'1'),(1353,'绵阳',32,'1'),(1354,'广元 ',32,'1'),(1355,'遂宁',32,'1'),(1356,'内江',32,'1'),(1357,'乐山',32,'1'),(1358,'南充',32,'1'),(1359,'宜宾',32,'1'),(1360,'广安',32,'1'),(1361,'达州 ',32,'1'),(1362,'眉山',32,'1'),(1363,'雅安',32,'1'),(1364,'巴中',32,'1'),(1365,'资阳',32,'1'),(1366,'阿坝藏族',32,'1'),(1367,'甘孜藏族',32,'1'),(1368,'凉山彝族 ',32,'1'),(1369,'贵阳',33,'1'),(1370,'六盘水',33,'1'),(1371,'遵义',33,'1'),(1372,'安顺',33,'1'),(1373,'铜仁',33,'1'),(1374,'毕节',33,'1'),(1375,'黔西南布 ',33,'1'),(1376,'黔东南苗',33,'1'),(1377,'黔南布依',33,'1'),(1378,'昆明',34,'1'),(1379,'曲靖',34,'1'),(1380,'玉溪',34,'1'),(1381,'保山',34,'1'),(1382,'昭通 ',34,'1'),(1383,'丽江',34,'1'),(1384,'思茅',34,'1'),(1385,'临沧',34,'1'),(1386,'文山壮族',34,'1'),(1387,'红河哈尼',34,'1'),(1388,'西双版纳',34,'1'),(1389,'楚雄彝族',34,'1'),(1390,'大理白族',34,'1'),(1391,'德宏傣族',34,'1'),(1392,'怒江傈傈 ',34,'1'),(1393,'迪庆藏族',34,'1'),(1394,'拉萨',35,'1'),(1395,'那曲',35,'1'),(1396,'昌都',35,'1'),(1397,'山南',35,'1'),(1398,'日喀则',35,'1'),(1399,'阿里 ',35,'1'),(1400,'林芝',35,'1'),(1401,'西安',36,'1'),(1402,'铜川',36,'1'),(1403,'宝鸡',36,'1'),(1404,'咸阳',36,'1'),(1405,'渭南',36,'1'),(1406,'延安 ',36,'1'),(1407,'汉中',36,'1'),(1408,'榆林',36,'1'),(1409,'安康',36,'1'),(1410,'商洛',36,'1'),(1411,'兰州',37,'1'),(1412,'金昌',37,'1'),(1413,'白银 ',37,'1'),(1414,'天水',37,'1'),(1415,'嘉峪关',37,'1'),(1416,'武威',37,'1'),(1417,'张掖',37,'1'),(1418,'平凉',37,'1'),(1419,'酒泉',37,'1'),(1420,'庆阳 ',37,'1'),(1421,'定西',37,'1'),(1422,'陇南',37,'1'),(1423,'临夏回族',37,'1'),(1424,'甘南藏族',37,'1'),(1425,'西宁',38,'1'),(1426,'海东',38,'1'),(1427,'海北藏族 ',38,'1'),(1428,'黄南藏族',38,'1'),(1429,'海南藏族',38,'1'),(1430,'果洛藏族 ',38,'1'),(1431,'玉树藏族',38,'1'),(1432,'海西蒙古',38,'1'),(1433,'银川',39,'1'),(1434,'石嘴山',39,'1'),(1435,'吴忠',39,'1'),(1436,'固原',39,'1'),(1437,'中卫 ',39,'1'),(1438,'乌鲁木齐',40,'1'),(1439,'克拉玛依',40,'1'),(1440,'石河子',40,'1'),(1441,'阿拉尔',40,'1'),(1442,'图木舒克',40,'1'),(1443,'五家渠',40,'1'),(1444,'吐鲁番 ',40,'1'),(1445,'哈密',40,'1'),(1446,'和田',40,'1'),(1447,'阿克苏',40,'1'),(1448,'喀什',40,'1'),(1449,'克孜勒苏',40,'1'),(1450,'巴音郭楞',40,'1'),(1451,'昌吉回族 ',40,'1'),(1452,'博尔塔拉',40,'1'),(1453,'伊犁哈萨',40,'1'),(1454,'塔城',40,'1'),(1455,'阿勒泰',40,'1'),(1456,'台北',43,'1'),(1457,'高雄',43,'1'),(1458,'基隆 ',43,'1'),(1459,'台中',43,'1'),(1460,'台南',43,'1'),(1461,'新竹',43,'1'),(1462,'嘉义',43,'1'),(1463,'台北县',43,'1'),(1464,'宜兰县',43,'1'),(1465,'新竹县 ',43,'1'),(1466,'桃园县',43,'1'),(1467,'苗栗县',43,'1'),(1468,'台中县',43,'1'),(1469,'彰化县',43,'1'),(1470,'南投县',43,'1'),(1471,'嘉义县',43,'1'),(1472,'云林县 ',43,'1'),(1473,'台南县',43,'1'),(1474,'高雄县',43,'1'),(1475,'屏东县',43,'1'),(1476,'台东县',43,'1'),(1477,'花莲县',43,'1'),(1478,'澎湖县',43,'1');

/*Table structure for table `ckd` */

DROP TABLE IF EXISTS `ckd`;

CREATE TABLE `ckd` (
  `djid` varchar(14) NOT NULL,
  `khid` int(11) DEFAULT NULL,
  `khname` varchar(50) DEFAULT NULL,
  `riqi` datetime NOT NULL,
  `yfje` double DEFAULT NULL,
  `sfje` double DEFAULT NULL,
  `cbje` double DEFAULT NULL,
  `jystate` varchar(2) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`djid`),
  KEY `FK180FC78D95FC5` (`khid`),
  KEY `FK180FC532696FE` (`userid`),
  CONSTRAINT `FK180FC532696FE` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`),
  CONSTRAINT `FK180FC78D95FC5` FOREIGN KEY (`khid`) REFERENCES `kh` (`khid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ckd` */

/*Table structure for table `ckdsp` */

DROP TABLE IF EXISTS `ckdsp`;

CREATE TABLE `ckdsp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `djid` varchar(14) NOT NULL,
  `spid` varchar(10) NOT NULL,
  `spname` varchar(20) DEFAULT NULL,
  `spdw` varchar(20) DEFAULT NULL,
  `spxinghao` varchar(20) DEFAULT NULL,
  `lbid` int(11) DEFAULT NULL,
  `lbname` varchar(20) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `zj` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5A540599C6CDBED` (`djid`),
  CONSTRAINT `FK5A540599C6CDBED` FOREIGN KEY (`djid`) REFERENCES `ckd` (`djid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ckdsp` */

/*Table structure for table `gys` */

DROP TABLE IF EXISTS `gys`;

CREATE TABLE `gys` (
  `gysid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '供应商名称',
  `lxren` varchar(100) DEFAULT NULL COMMENT '联系人',
  `lxtel` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `bz` varchar(200) DEFAULT NULL COMMENT '备注',
  `companyid` int(11) DEFAULT NULL COMMENT '所属企业',
  `number` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `fsn` varchar(50) DEFAULT NULL COMMENT '供应商编号',
  PRIMARY KEY (`gysid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gys` */

/*Table structure for table `jhd` */

DROP TABLE IF EXISTS `jhd`;

CREATE TABLE `jhd` (
  `djid` varchar(14) NOT NULL,
  `gysid` int(11) DEFAULT NULL,
  `gysname` varchar(50) DEFAULT NULL,
  `riqi` datetime NOT NULL,
  `yfje` double DEFAULT NULL,
  `sfje` double DEFAULT NULL,
  `jystate` varchar(2) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`djid`),
  KEY `FK19AE6A2220F2D` (`gysid`),
  KEY `FK19AE6532696FE` (`userid`),
  CONSTRAINT `FK19AE6532696FE` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`),
  CONSTRAINT `FK19AE6A2220F2D` FOREIGN KEY (`gysid`) REFERENCES `gys` (`gysid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jhd` */

/*Table structure for table `jhdsp` */

DROP TABLE IF EXISTS `jhdsp`;

CREATE TABLE `jhdsp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `djid` varchar(14) NOT NULL,
  `spid` varchar(10) NOT NULL,
  `spname` varchar(20) DEFAULT NULL,
  `spdw` varchar(20) DEFAULT NULL,
  `spxinghao` varchar(20) DEFAULT NULL,
  `lbid` int(11) DEFAULT NULL,
  `lbname` varchar(20) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `zj` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60687C39C6CF5D7` (`djid`),
  CONSTRAINT `FK60687C39C6CF5D7` FOREIGN KEY (`djid`) REFERENCES `jhd` (`djid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jhdsp` */

/*Table structure for table `kh` */

DROP TABLE IF EXISTS `kh`;

CREATE TABLE `kh` (
  `khid` int(11) NOT NULL AUTO_INCREMENT,
  `khname` varchar(100) NOT NULL COMMENT '名称',
  `pycode` varchar(50) DEFAULT NULL COMMENT '名称的拼音码',
  `lxren` varchar(100) DEFAULT NULL COMMENT '联系人',
  `lxtel` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `bz` varchar(200) DEFAULT NULL COMMENT '备注',
  `country` varchar(20) DEFAULT NULL COMMENT '国家',
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `distincts` varchar(20) DEFAULT NULL COMMENT '区/县',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机',
  `fax` varchar(100) DEFAULT NULL COMMENT '传真',
  `qq` varchar(50) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `shipping_address` varchar(100) DEFAULT NULL COMMENT '常用送货地址',
  `shippint_clear` varchar(100) DEFAULT NULL COMMENT '常用结算方式',
  `shipping_type` varchar(100) DEFAULT NULL COMMENT '常用配送方式',
  `shipping_log` varchar(100) DEFAULT NULL COMMENT '物流公司',
  `credit` int(11) DEFAULT NULL COMMENT '信用度',
  `khnum` varchar(20) DEFAULT NULL COMMENT '客户编号',
  `companyid` int(11) DEFAULT NULL COMMENT '录入部门',
  `deOwe` double DEFAULT NULL COMMENT '客户欠款',
  PRIMARY KEY (`khid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `kh` */

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menuid` int(11) NOT NULL AUTO_INCREMENT,
  `menuname` varchar(20) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `menuurl` varchar(100) DEFAULT NULL,
  `menutype` int(11) NOT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `icon` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB AUTO_INCREMENT=9051 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`menuid`,`menuname`,`pid`,`menuurl`,`menutype`,`ordernum`,`icon`) values (1,'系统菜单',-1,NULL,1,0,'menu-plugin'),(50,'基础资料',0,NULL,1,5,'menu-5'),(60,'系统管理',0,NULL,1,6,'menu-6'),(80,'云通货',0,NULL,1,8,'menu-1'),(90,'云通宝',0,NULL,1,9,'menu-3'),(5010,'供应商管理',50,'../ziliao/gys.jsp',0,1,'menu-51'),(5020,'客户管理',50,'../ziliao/kh.jsp',0,2,'menu-52'),(5030,'商品管理',50,'../ziliao/spxx.jsp',0,3,'menu-53'),(5040,'期初库存',50,'../ziliao/kc.jsp',0,4,'menu-54'),(5050,'企业管理',60,'../power/company.jsp',0,4,'menu-64'),(5060,'仓库管理',50,'../ziliao/warehouse.jsp',0,6,NULL),(5070,'计量单位管理',50,'../ziliao/unit.jsp',0,7,NULL),(5080,'结算方式管理',50,'../ziliao/settlement.jsp',0,8,NULL),(5090,'配送方式管理',50,'../ziliao/logistics.jsp',0,9,NULL),(5100,'物流公司',50,'../ziliao/logisticscompany.jsp',0,10,NULL),(5110,'账目类型',50,'../ziliao/accounttype.jsp',0,11,NULL),(5120,'账户',50,'../ziliao/account.jsp',0,12,NULL),(6010,'角色管理',60,'../power/role.jsp',0,1,'menu-61'),(6020,'用户管理',60,'../power/user.jsp',0,2,'menu-62'),(6030,'数据库管理',60,'../power/beifen.jsp',3,3,'menu-63'),(6050,'用户权限',60,'../power/userright.jsp',0,5,NULL),(6060,'模板设置',60,'../power/printset.jsp',0,6,NULL),(7010,'职位管理',50,'../ziliao/position.jsp',0,1,'menu-71'),(7020,'员工资料',50,'../ziliao/person.jsp',0,2,NULL),(8010,'购进',80,'../purchase/purchase.jsp',0,1,NULL),(8020,'购退',80,'../purchase/return.jsp',0,2,NULL),(8030,'销售',80,'../purchase/salenout.jsp',0,3,NULL),(8040,'销退',80,'../purchase/salenback.jsp',0,4,NULL),(8050,'调拨',80,'../purchase/allocation.jsp',0,5,NULL),(8060,'库存盘点',80,'../purchase/inventory.jsp',0,6,NULL),(8070,'拼包',80,'../purchase/spellpack.jsp',0,7,NULL),(8080,'送货登记',80,'../purchase/delivery.jsp',0,8,NULL),(8090,'库存查询',80,'../purchase/stocklist.jsp',0,9,NULL),(8100,'信封打印',80,'../purchase/envelopeprint.jsp',0,10,NULL),(8110,'今日报表',80,'../purchase/today.jsp',0,11,NULL),(8120,'进出商品查询',80,'../purchase/myjcsp.jsp',0,12,NULL),(9010,'收入',90,'../financial/income.jsp',0,1,NULL),(9020,'支出',90,'../financial/outcome.jsp',0,2,NULL),(9030,'应收款',90,'../financial/ar.jsp',0,3,NULL),(9040,'应付款',90,'../financial/ap.jsp',0,4,NULL),(9050,'资金余额',90,'../financial/balance.jsp',0,5,NULL);

/*Table structure for table `province` */

DROP TABLE IF EXISTS `province`;

CREATE TABLE `province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proname` varchar(30) NOT NULL,
  `type` varchar(1) DEFAULT NULL COMMENT '1 - 直辖市 2 - 行政省 3 - 自治区 4 - 特别行政区 5 - 其他国家 见全局数据字典[省份类型]',
  `state` varchar(1) DEFAULT NULL COMMENT '0 - 禁用 1 - 启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `province` */

insert  into `province`(`id`,`proname`,`type`,`state`) values (1,'国外','5','1'),(10,'北京','1','1'),(11,'上海','1','1'),(12,'天津','1','1'),(13,'重庆','1','1'),(14,'河北','2','1'),(15,'山西','2','1'),(16,'内蒙古 ','3','1'),(17,'辽宁','2','1'),(18,'吉林','2','1'),(19,'黑龙江','2','1'),(20,'江苏','2','1'),(21,'浙江','2','1'),(22,'安徽','2','1'),(23,'福建','2','1'),(24,'江西','2','1'),(25,'山东','2','1'),(26,'河南','2','1'),(27,'湖北','2','1'),(28,'湖南','2','1'),(29,'广东','2','1'),(30,'广西','3','1'),(31,'海南','2','1'),(32,'四川','2','1'),(33,'贵州','2','1'),(34,'云南','2','1'),(35,'西藏','3','1'),(36,'陕西','2','1'),(37,'甘肃','2','1'),(38,'青海','2','1'),(39,'宁夏','3','1'),(40,'新疆','3','1'),(41,'香港','4','1'),(42,'澳门','4','1'),(43,'台湾','2','1');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(20) NOT NULL,
  `bz` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleid`,`rolename`,`bz`) values (1,'管理员','系统管理'),(4,'销售','前台销售'),(5,'用户','普通用户');

/*Table structure for table `rolemenu` */

DROP TABLE IF EXISTS `rolemenu`;

CREATE TABLE `rolemenu` (
  `roleid` int(11) NOT NULL,
  `menuid` int(11) NOT NULL,
  PRIMARY KEY (`roleid`,`menuid`),
  KEY `FKF0276AD5B459D7B7` (`roleid`),
  KEY `FKF0276AD5AB436B49` (`menuid`),
  CONSTRAINT `FKF0276AD5AB436B49` FOREIGN KEY (`menuid`) REFERENCES `menu` (`menuid`),
  CONSTRAINT `FKF0276AD5B459D7B7` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rolemenu` */

insert  into `rolemenu`(`roleid`,`menuid`) values (1,50),(1,60),(1,80),(1,90),(1,5010),(1,5020),(1,5030),(1,5040),(1,5050),(1,5060),(1,5070),(1,5080),(1,5090),(1,5100),(1,5110),(1,5120),(1,6010),(1,6020),(1,6030),(1,6050),(1,6060),(1,7010),(1,7020),(1,8010),(1,8020),(1,8030),(1,8040),(1,8050),(1,8060),(1,8070),(1,8080),(1,8090),(1,8100),(1,8110),(1,8120),(1,9010),(1,9020),(1,9030),(1,9040),(1,9050),(4,50),(4,60),(4,80),(4,90),(4,5010),(4,5020),(4,5030),(4,5040),(4,5060),(4,5070),(4,5080),(4,5090),(4,5100),(4,5110),(4,5120),(4,6060),(4,7010),(4,7020),(4,8010),(4,8020),(4,8030),(4,8040),(4,8050),(4,8060),(4,8070),(4,8080),(4,8090),(4,8100),(4,8110),(4,9010),(4,9020),(4,9030),(4,9040),(4,9050);

/*Table structure for table `spdw` */

DROP TABLE IF EXISTS `spdw`;

CREATE TABLE `spdw` (
  `dwid` int(11) NOT NULL AUTO_INCREMENT,
  `dwname` varchar(20) NOT NULL,
  PRIMARY KEY (`dwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `spdw` */

/*Table structure for table `splb` */

DROP TABLE IF EXISTS `splb`;

CREATE TABLE `splb` (
  `lbid` int(11) NOT NULL AUTO_INCREMENT,
  `lbname` varchar(20) DEFAULT NULL COMMENT '类别名称',
  `pid` int(11) DEFAULT NULL COMMENT '父类别id',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`lbid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `splb` */

insert  into `splb`(`lbid`,`lbname`,`pid`,`companyId`) values (1,'ss',0,NULL);

/*Table structure for table `spxx` */

DROP TABLE IF EXISTS `spxx`;

CREATE TABLE `spxx` (
  `spid` varchar(10) NOT NULL,
  `spname` varchar(20) NOT NULL,
  `xinghao` varchar(20) DEFAULT NULL,
  `lbid` int(11) NOT NULL,
  `lbname` varchar(20) NOT NULL,
  `dw` varchar(10) DEFAULT NULL,
  `jhprice` double DEFAULT NULL,
  `chprice` double DEFAULT NULL,
  `scjj` double DEFAULT NULL,
  `kcsl` int(11) DEFAULT NULL,
  `kczj` double DEFAULT NULL,
  `minnum` int(11) DEFAULT NULL,
  `csname` varchar(50) DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  `companyid` int(11) DEFAULT NULL,
  PRIMARY KEY (`spid`),
  KEY `FK35FA1DEBB59CD4` (`lbid`),
  CONSTRAINT `FK35FA1DEBB59CD4` FOREIGN KEY (`lbid`) REFERENCES `splb` (`lbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `spxx` */

/*Table structure for table `tbaccount` */

DROP TABLE IF EXISTS `tbaccount`;

CREATE TABLE `tbaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '名称',
  `iType` int(11) DEFAULT NULL COMMENT '账户类型(0、支出,1、收入)',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbaccount` */

/*Table structure for table `tbaccounttype` */

DROP TABLE IF EXISTS `tbaccounttype`;

CREATE TABLE `tbaccounttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `itype` int(11) DEFAULT NULL COMMENT '账目类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbaccounttype` */

/*Table structure for table `tballocation` */

DROP TABLE IF EXISTS `tballocation`;

CREATE TABLE `tballocation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '单据编号',
  `outId` int(11) DEFAULT NULL COMMENT '调出仓库',
  `inId` int(11) DEFAULT NULL COMMENT '调入仓库',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '日期',
  `dlCount` double DEFAULT NULL COMMENT '数量',
  `dlMoney` double DEFAULT NULL COMMENT '金额',
  `iState` int(11) DEFAULT NULL COMMENT '状态',
  `userId` int(11) DEFAULT NULL COMMENT '制单人',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tballocation` */

/*Table structure for table `tballocationdel` */

DROP TABLE IF EXISTS `tballocationdel`;

CREATE TABLE `tballocationdel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `allocationId` int(11) DEFAULT NULL COMMENT '调拨Id',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品Id',
  `vcBatch` varchar(20) DEFAULT NULL COMMENT '批次',
  `vcSn` varchar(20) DEFAULT NULL COMMENT '辅助标识',
  `dlCount` double DEFAULT NULL COMMENT '库存数量',
  `vcDw` varchar(10) DEFAULT NULL COMMENT '单位',
  `dlMoney` double DEFAULT NULL COMMENT '金额',
  `outId` int(11) DEFAULT NULL COMMENT '调出仓库Id',
  `inId` int(11) DEFAULT NULL COMMENT '调入仓库Id',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `comstockId` int(11) DEFAULT NULL COMMENT '来源库存Id(调出库存Id)',
  `gostockId` int(11) DEFAULT NULL COMMENT '生成的新库存Id(调入库存)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tballocationdel` */

/*Table structure for table `tbap` */

DROP TABLE IF EXISTS `tbap`;

CREATE TABLE `tbap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(15) DEFAULT NULL COMMENT '编号',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `gysId` int(11) DEFAULT NULL COMMENT '供应商Id',
  `sourceId` int(11) DEFAULT NULL COMMENT '购进相关的Id',
  `sourceType` int(11) DEFAULT NULL COMMENT '来源类型(0:购进、1:购退)',
  `logisticsId` int(11) DEFAULT NULL COMMENT '配送方式',
  `settlementId` int(11) DEFAULT NULL COMMENT '结算方式',
  `dlMoney` double DEFAULT NULL COMMENT '金额',
  `istate` int(11) DEFAULT NULL COMMENT '结算状态(0:未结算、1:已结算、-1:已作废)',
  `vcAuditor` varchar(20) DEFAULT NULL COMMENT '经办人',
  `dtJsDate` varchar(15) DEFAULT NULL COMMENT '结算日期',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `dtWrite` varchar(15) DEFAULT NULL COMMENT '写入日期',
  `userId` int(11) DEFAULT NULL COMMENT '制单人',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `fidel` int(11) DEFAULT NULL COMMENT '是否作废(0:未作废、1:已作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbap` */

/*Table structure for table `tbar` */

DROP TABLE IF EXISTS `tbar`;

CREATE TABLE `tbar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '编号',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `khId` int(11) DEFAULT NULL COMMENT '客户Id',
  `sourceId` int(11) DEFAULT NULL COMMENT '销售相关的单据Id',
  `sourceType` int(11) DEFAULT NULL COMMENT '来源类型(0:销售，1:销退)',
  `logisticsId` int(11) DEFAULT NULL COMMENT '配送方式',
  `settlementId` int(11) DEFAULT NULL COMMENT '结算方式',
  `dlMoney` double DEFAULT NULL COMMENT '金额',
  `istate` int(11) DEFAULT NULL COMMENT '结算状态(0:未结算、1:已结算、-1:已作废)',
  `vcAuditor` varchar(20) DEFAULT NULL COMMENT '经办人',
  `dtJsDate` varchar(15) DEFAULT NULL COMMENT '结算日期',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `dtWrite` varchar(15) NOT NULL COMMENT '写入日期',
  `userId` int(11) DEFAULT NULL COMMENT '制单人',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `fidel` int(11) DEFAULT NULL COMMENT '是否作废(0:未作废、1:已作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbar` */

/*Table structure for table `tbarea` */

DROP TABLE IF EXISTS `tbarea`;

CREATE TABLE `tbarea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNumber` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(20) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `cityId` int(11) DEFAULT NULL COMMENT '城市ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2887 DEFAULT CHARSET=utf8;

/*Data for the table `tbarea` */

insert  into `tbarea`(`id`,`vcNumber`,`vcName`,`vcRemark`,`cityId`) values (1,NULL,'东城区',NULL,1),(2,NULL,'西城区',NULL,1),(5,NULL,'朝阳区',NULL,1),(6,NULL,'丰台区',NULL,1),(7,NULL,'石景山区',NULL,1),(8,NULL,'海淀区',NULL,1),(9,NULL,'门头沟区',NULL,1),(10,NULL,'房山区',NULL,1),(11,NULL,'通州区',NULL,1),(12,NULL,'顺义区',NULL,1),(13,NULL,'昌平区',NULL,1),(14,NULL,'大兴区',NULL,1),(15,NULL,'怀柔区',NULL,1),(16,NULL,'平谷区',NULL,1),(17,NULL,'密云县',NULL,1),(18,NULL,'延庆县',NULL,1),(19,NULL,'和平区',NULL,2),(20,NULL,'河东区',NULL,2),(21,NULL,'河西区',NULL,2),(22,NULL,'南开区',NULL,2),(23,NULL,'河北区',NULL,2),(24,NULL,'红桥区',NULL,2),(28,NULL,'东丽区',NULL,2),(29,NULL,'西青区',NULL,2),(30,NULL,'津南区',NULL,2),(31,NULL,'北辰区',NULL,2),(32,NULL,'武清区',NULL,2),(33,NULL,'宝坻区',NULL,2),(34,NULL,'宁河县',NULL,2),(35,NULL,'静海县',NULL,2),(36,NULL,'蓟县',NULL,2),(37,NULL,'长安区',NULL,3),(38,NULL,'桥东区',NULL,3),(39,NULL,'桥西区',NULL,3),(40,NULL,'新华区',NULL,3),(41,NULL,'井陉矿区',NULL,3),(42,NULL,'裕华区',NULL,3),(43,NULL,'井陉县',NULL,3),(44,NULL,'正定县',NULL,3),(45,NULL,'栾城县',NULL,3),(46,NULL,'行唐县',NULL,3),(47,NULL,'灵寿县',NULL,3),(48,NULL,'高邑县',NULL,3),(49,NULL,'深泽县',NULL,3),(50,NULL,'赞皇县',NULL,3),(51,NULL,'无极县',NULL,3),(52,NULL,'平山县',NULL,3),(53,NULL,'元氏县',NULL,3),(54,NULL,'赵县',NULL,3),(55,NULL,'辛集市',NULL,3),(56,NULL,'藁城市',NULL,3),(57,NULL,'晋州市',NULL,3),(58,NULL,'新乐市',NULL,3),(59,NULL,'鹿泉市',NULL,3),(60,NULL,'路南区',NULL,4),(61,NULL,'路北区',NULL,4),(63,NULL,'开平区',NULL,4),(64,NULL,'丰南区',NULL,4),(65,NULL,'丰润区',NULL,4),(66,NULL,'滦县',NULL,4),(67,NULL,'滦南县',NULL,4),(68,NULL,'乐亭县',NULL,4),(69,NULL,'迁西县',NULL,4),(70,NULL,'玉田县',NULL,4),(72,NULL,'遵化市',NULL,4),(73,NULL,'迁安市',NULL,4),(74,NULL,'海港区',NULL,5),(75,NULL,'山海关区',NULL,5),(76,NULL,'北戴河区',NULL,5),(77,NULL,'青龙满族自治县',NULL,5),(78,NULL,'昌黎县',NULL,5),(79,NULL,'抚宁县',NULL,5),(80,NULL,'卢龙县',NULL,5),(81,NULL,'邯山区',NULL,6),(82,NULL,'丛台区',NULL,6),(83,NULL,'复兴区',NULL,6),(84,NULL,'峰峰矿区',NULL,6),(85,NULL,'邯郸县',NULL,6),(86,NULL,'临漳县',NULL,6),(87,NULL,'成安县',NULL,6),(88,NULL,'大名县',NULL,6),(89,NULL,'涉县',NULL,6),(90,NULL,'磁县',NULL,6),(91,NULL,'肥乡县',NULL,6),(92,NULL,'永年县',NULL,6),(93,NULL,'邱县',NULL,6),(94,NULL,'鸡泽县',NULL,6),(95,NULL,'广平县',NULL,6),(96,NULL,'馆陶县',NULL,6),(97,NULL,'魏县',NULL,6),(98,NULL,'曲周县',NULL,6),(99,NULL,'武安市',NULL,6),(100,NULL,'桥东区',NULL,7),(101,NULL,'桥西区',NULL,7),(102,NULL,'邢台县',NULL,7),(103,NULL,'临城县',NULL,7),(104,NULL,'内丘县',NULL,7),(105,NULL,'柏乡县',NULL,7),(106,NULL,'隆尧县',NULL,7),(107,NULL,'任县',NULL,7),(108,NULL,'南和县',NULL,7),(109,NULL,'宁晋县',NULL,7),(110,NULL,'巨鹿县',NULL,7),(111,NULL,'新河县',NULL,7),(112,NULL,'广宗县',NULL,7),(113,NULL,'平乡县',NULL,7),(114,NULL,'威县',NULL,7),(115,NULL,'清河县',NULL,7),(116,NULL,'临西县',NULL,7),(117,NULL,'南宫市',NULL,7),(118,NULL,'沙河市',NULL,7),(119,NULL,'新市区',NULL,8),(120,NULL,'北市区',NULL,8),(121,NULL,'南市区',NULL,8),(122,NULL,'满城县',NULL,8),(123,NULL,'清苑县',NULL,8),(124,NULL,'涞水县',NULL,8),(125,NULL,'阜平县',NULL,8),(126,NULL,'徐水县',NULL,8),(127,NULL,'定兴县',NULL,8),(128,NULL,'唐县',NULL,8),(129,NULL,'高阳县',NULL,8),(130,NULL,'容城县',NULL,8),(131,NULL,'涞源县',NULL,8),(132,NULL,'望都县',NULL,8),(133,NULL,'安新县',NULL,8),(134,NULL,'易县',NULL,8),(135,NULL,'曲阳县',NULL,8),(136,NULL,'蠡县',NULL,8),(137,NULL,'顺平县',NULL,8),(138,NULL,'博野县',NULL,8),(139,NULL,'雄县',NULL,8),(140,NULL,'涿州市',NULL,8),(141,NULL,'定州市',NULL,8),(142,NULL,'安国市',NULL,8),(143,NULL,'高碑店市',NULL,8),(144,NULL,'桥东区',NULL,9),(145,NULL,'桥西区',NULL,9),(146,NULL,'宣化区',NULL,9),(147,NULL,'下花园区',NULL,9),(148,NULL,'宣化县',NULL,9),(149,NULL,'张北县',NULL,9),(150,NULL,'康保县',NULL,9),(151,NULL,'沽源县',NULL,9),(152,NULL,'尚义县',NULL,9),(153,NULL,'蔚县',NULL,9),(154,NULL,'阳原县',NULL,9),(155,NULL,'怀安县',NULL,9),(156,NULL,'万全县',NULL,9),(157,NULL,'怀来县',NULL,9),(158,NULL,'涿鹿县',NULL,9),(159,NULL,'赤城县',NULL,9),(160,NULL,'崇礼县',NULL,9),(161,NULL,'双桥区',NULL,10),(162,NULL,'双滦区',NULL,10),(164,NULL,'承德县',NULL,10),(165,NULL,'兴隆县',NULL,10),(166,NULL,'平泉县',NULL,10),(167,NULL,'滦平县',NULL,10),(168,NULL,'隆化县',NULL,10),(169,NULL,'丰宁满族自治县',NULL,10),(170,NULL,'宽城满族自治县',NULL,10),(171,NULL,'围场满族蒙古族自治县',NULL,10),(172,NULL,'新华区',NULL,11),(173,NULL,'运河区',NULL,11),(174,NULL,'沧县',NULL,11),(175,NULL,'青县',NULL,11),(176,NULL,'东光县',NULL,11),(177,NULL,'海兴县',NULL,11),(178,NULL,'盐山县',NULL,11),(179,NULL,'肃宁县',NULL,11),(180,NULL,'南皮县',NULL,11),(181,NULL,'吴桥县',NULL,11),(182,NULL,'献县',NULL,11),(183,NULL,'孟村回族自治县',NULL,11),(184,NULL,'泊头市',NULL,11),(185,NULL,'任丘市',NULL,11),(186,NULL,'黄骅市',NULL,11),(187,NULL,'河间市',NULL,11),(188,NULL,'安次区',NULL,12),(189,NULL,'广阳区',NULL,12),(190,NULL,'固安县',NULL,12),(191,NULL,'永清县',NULL,12),(192,NULL,'香河县',NULL,12),(193,NULL,'大城县',NULL,12),(194,NULL,'文安县',NULL,12),(195,NULL,'大厂回族自治县',NULL,12),(196,NULL,'霸州市',NULL,12),(197,NULL,'三河市',NULL,12),(198,NULL,'桃城区',NULL,13),(199,NULL,'枣强县',NULL,13),(200,NULL,'武邑县',NULL,13),(201,NULL,'武强县',NULL,13),(202,NULL,'饶阳县',NULL,13),(203,NULL,'安平县',NULL,13),(204,NULL,'故城县',NULL,13),(205,NULL,'景县',NULL,13),(206,NULL,'阜城县',NULL,13),(207,NULL,'冀州市',NULL,13),(208,NULL,'深州市',NULL,13),(209,NULL,'小店区',NULL,14),(210,NULL,'迎泽区',NULL,14),(211,NULL,'杏花岭区',NULL,14),(212,NULL,'尖草坪区',NULL,14),(213,NULL,'万柏林区',NULL,14),(214,NULL,'晋源区',NULL,14),(215,NULL,'清徐县',NULL,14),(216,NULL,'阳曲县',NULL,14),(217,NULL,'娄烦县',NULL,14),(218,NULL,'古交市',NULL,14),(219,NULL,'城区',NULL,15),(220,NULL,'矿区',NULL,15),(221,NULL,'南郊区',NULL,15),(222,NULL,'新荣区',NULL,15),(223,NULL,'阳高县',NULL,15),(224,NULL,'天镇县',NULL,15),(225,NULL,'广灵县',NULL,15),(226,NULL,'灵丘县',NULL,15),(227,NULL,'浑源县',NULL,15),(228,NULL,'左云县',NULL,15),(229,NULL,'大同县',NULL,15),(230,NULL,'城区',NULL,16),(231,NULL,'矿区',NULL,16),(232,NULL,'郊区',NULL,16),(233,NULL,'平定县',NULL,16),(234,NULL,'盂县',NULL,16),(235,NULL,'城区',NULL,17),(236,NULL,'郊区',NULL,17),(237,NULL,'长治县',NULL,17),(238,NULL,'襄垣县',NULL,17),(239,NULL,'屯留县',NULL,17),(240,NULL,'平顺县',NULL,17),(241,NULL,'黎城县',NULL,17),(242,NULL,'壶关县',NULL,17),(243,NULL,'长子县',NULL,17),(244,NULL,'武乡县',NULL,17),(245,NULL,'沁县',NULL,17),(246,NULL,'沁源县',NULL,17),(247,NULL,'潞城市',NULL,17),(248,NULL,'城区',NULL,18),(249,NULL,'沁水县',NULL,18),(250,NULL,'阳城县',NULL,18),(251,NULL,'陵川县',NULL,18),(252,NULL,'泽州县',NULL,18),(253,NULL,'高平市',NULL,18),(254,NULL,'朔城区',NULL,19),(255,NULL,'平鲁区',NULL,19),(256,NULL,'山阴县',NULL,19),(257,NULL,'应县',NULL,19),(258,NULL,'右玉县',NULL,19),(259,NULL,'怀仁县',NULL,19),(260,NULL,'榆次区',NULL,20),(261,NULL,'榆社县',NULL,20),(262,NULL,'左权县',NULL,20),(264,NULL,'昔阳县',NULL,20),(265,NULL,'寿阳县',NULL,20),(266,NULL,'太谷县',NULL,20),(267,NULL,'祁县',NULL,20),(268,NULL,'平遥县',NULL,20),(269,NULL,'灵石县',NULL,20),(270,NULL,'介休市',NULL,20),(271,NULL,'盐湖区',NULL,21),(272,NULL,'临猗县',NULL,21),(273,NULL,'万荣县',NULL,21),(274,NULL,'闻喜县',NULL,21),(275,NULL,'稷山县',NULL,21),(276,NULL,'新绛县',NULL,21),(277,NULL,'绛县',NULL,21),(278,NULL,'垣曲县',NULL,21),(279,NULL,'夏县',NULL,21),(280,NULL,'平陆县',NULL,21),(281,NULL,'芮城县',NULL,21),(282,NULL,'永济市',NULL,21),(283,NULL,'河津市',NULL,21),(284,NULL,'忻府区',NULL,22),(285,NULL,'定襄县',NULL,22),(286,NULL,'五台县',NULL,22),(287,NULL,'代县',NULL,22),(288,NULL,'繁峙县',NULL,22),(289,NULL,'宁武县',NULL,22),(290,NULL,'静乐县',NULL,22),(291,NULL,'神池县',NULL,22),(292,NULL,'五寨县',NULL,22),(293,NULL,'岢岚县',NULL,22),(294,NULL,'河曲县',NULL,22),(295,NULL,'保德县',NULL,22),(296,NULL,'偏关县',NULL,22),(297,NULL,'原平市',NULL,22),(298,NULL,'尧都区',NULL,23),(299,NULL,'曲沃县',NULL,23),(300,NULL,'翼城县',NULL,23),(301,NULL,'襄汾县',NULL,23),(302,NULL,'洪洞县',NULL,23),(303,NULL,'古县',NULL,23),(304,NULL,'安泽县',NULL,23),(305,NULL,'浮山县',NULL,23),(306,NULL,'吉县',NULL,23),(307,NULL,'乡宁县',NULL,23),(308,NULL,'大宁县',NULL,23),(309,NULL,'隰县',NULL,23),(310,NULL,'永和县',NULL,23),(311,NULL,'蒲县',NULL,23),(312,NULL,'汾西县',NULL,23),(313,NULL,'侯马市',NULL,23),(314,NULL,'霍州市',NULL,23),(315,NULL,'离石区',NULL,24),(316,NULL,'文水县',NULL,24),(317,NULL,'交城县',NULL,24),(318,NULL,'兴县',NULL,24),(319,NULL,'临县',NULL,24),(320,NULL,'柳林县',NULL,24),(321,NULL,'石楼县',NULL,24),(322,NULL,'岚县',NULL,24),(323,NULL,'方山县',NULL,24),(324,NULL,'中阳县',NULL,24),(325,NULL,'交口县',NULL,24),(326,NULL,'孝义市',NULL,24),(327,NULL,'汾阳市',NULL,24),(328,NULL,'新城区',NULL,25),(329,NULL,'回民区',NULL,25),(330,NULL,'玉泉区',NULL,25),(331,NULL,'赛罕区',NULL,25),(332,NULL,'土默特左旗',NULL,25),(333,NULL,'托克托县',NULL,25),(334,NULL,'和林格尔县',NULL,25),(335,NULL,'清水河县',NULL,25),(336,NULL,'武川县',NULL,25),(337,NULL,'东河区',NULL,26),(338,NULL,'昆都仑区',NULL,26),(339,NULL,'青山区',NULL,26),(340,NULL,'石拐区',NULL,26),(341,NULL,'白云鄂博矿区',NULL,26),(342,NULL,'九原区',NULL,26),(343,NULL,'土默特右旗',NULL,26),(344,NULL,'固阳县',NULL,26),(345,NULL,'达尔罕茂明安联合旗',NULL,26),(346,NULL,'海勃湾区',NULL,27),(347,NULL,'海南区',NULL,27),(348,NULL,'乌达区',NULL,27),(349,NULL,'红山区',NULL,28),(350,NULL,'元宝山区',NULL,28),(351,NULL,'松山区',NULL,28),(352,NULL,'阿鲁科尔沁旗',NULL,28),(353,NULL,'巴林左旗',NULL,28),(354,NULL,'巴林右旗',NULL,28),(355,NULL,'林西县',NULL,28),(356,NULL,'克什克腾旗',NULL,28),(357,NULL,'翁牛特旗',NULL,28),(358,NULL,'喀喇沁旗',NULL,28),(359,NULL,'宁城县',NULL,28),(360,NULL,'敖汉旗',NULL,28),(361,NULL,'科尔沁区',NULL,29),(362,NULL,'科尔沁左翼中旗',NULL,29),(364,NULL,'开鲁县',NULL,29),(365,NULL,'库伦旗',NULL,29),(366,NULL,'奈曼旗',NULL,29),(367,NULL,'扎鲁特旗',NULL,29),(368,NULL,'霍林郭勒市',NULL,29),(369,NULL,'东胜区',NULL,30),(370,NULL,'达拉特旗',NULL,30),(371,NULL,'准格尔旗',NULL,30),(372,NULL,'鄂托克前旗',NULL,30),(373,NULL,'鄂托克旗',NULL,30),(374,NULL,'杭锦旗',NULL,30),(375,NULL,'乌审旗',NULL,30),(376,NULL,'伊金霍洛旗',NULL,30),(377,NULL,'海拉尔区',NULL,31),(378,NULL,'阿荣旗',NULL,31),(379,NULL,'莫力达瓦达斡尔族自治旗',NULL,31),(380,NULL,'鄂伦春自治旗',NULL,31),(381,NULL,'鄂温克族自治旗',NULL,31),(382,NULL,'陈巴尔虎旗',NULL,31),(383,NULL,'新巴尔虎左旗',NULL,31),(384,NULL,'新巴尔虎右旗',NULL,31),(385,NULL,'满洲里市',NULL,31),(386,NULL,'牙克石市',NULL,31),(387,NULL,'扎兰屯市',NULL,31),(388,NULL,'额尔古纳市',NULL,31),(389,NULL,'根河市',NULL,31),(390,NULL,'临河区',NULL,32),(391,NULL,'五原县',NULL,32),(392,NULL,'磴口县',NULL,32),(393,NULL,'乌拉特前旗',NULL,32),(394,NULL,'乌拉特中旗',NULL,32),(395,NULL,'乌拉特后旗',NULL,32),(396,NULL,'杭锦后旗',NULL,32),(397,NULL,'集宁区',NULL,33),(398,NULL,'卓资县',NULL,33),(399,NULL,'化德县',NULL,33),(400,NULL,'商都县',NULL,33),(401,NULL,'兴和县',NULL,33),(402,NULL,'凉城县',NULL,33),(403,NULL,'察哈尔右翼前旗',NULL,33),(404,NULL,'察哈尔右翼中旗',NULL,33),(405,NULL,'察哈尔右翼后旗',NULL,33),(406,NULL,'四子王旗',NULL,33),(407,NULL,'丰镇市',NULL,33),(408,NULL,'乌兰浩特市',NULL,34),(409,NULL,'阿尔山市',NULL,34),(410,NULL,'科尔沁右翼前旗',NULL,34),(411,NULL,'科尔沁右翼中旗',NULL,34),(412,NULL,'扎赉特旗',NULL,34),(413,NULL,'突泉县',NULL,34),(414,NULL,'二连浩特市',NULL,35),(415,NULL,'锡林浩特市',NULL,35),(416,NULL,'阿巴嘎旗',NULL,35),(417,NULL,'苏尼特左旗',NULL,35),(418,NULL,'苏尼特右旗',NULL,35),(419,NULL,'东乌珠穆沁旗',NULL,35),(420,NULL,'西乌珠穆沁旗',NULL,35),(421,NULL,'太仆寺旗',NULL,35),(422,NULL,'镶黄旗',NULL,35),(423,NULL,'正镶白旗',NULL,35),(424,NULL,'正蓝旗',NULL,35),(425,NULL,'多伦县',NULL,35),(426,NULL,'阿拉善左旗',NULL,36),(427,NULL,'阿拉善右旗',NULL,36),(428,NULL,'额济纳旗',NULL,36),(429,NULL,'和平区',NULL,37),(430,NULL,'沈河区',NULL,37),(431,NULL,'大东区',NULL,37),(432,NULL,'皇姑区',NULL,37),(433,NULL,'铁西区',NULL,37),(434,NULL,'苏家屯区',NULL,37),(435,NULL,'东陵区',NULL,37),(436,NULL,'沈北新区',NULL,37),(437,NULL,'于洪区',NULL,37),(438,NULL,'辽中县',NULL,37),(439,NULL,'康平县',NULL,37),(440,NULL,'法库县',NULL,37),(441,NULL,'新民市',NULL,37),(442,NULL,'中山区',NULL,38),(443,NULL,'西岗区',NULL,38),(444,NULL,'沙河口区',NULL,38),(445,NULL,'甘井子区',NULL,38),(446,NULL,'旅顺口区',NULL,38),(447,NULL,'金州区',NULL,38),(448,NULL,'长海县',NULL,38),(449,NULL,'瓦房店市',NULL,38),(450,NULL,'普兰店市',NULL,38),(451,NULL,'庄河市',NULL,38),(452,NULL,'铁东区',NULL,39),(453,NULL,'铁西区',NULL,39),(454,NULL,'立山区',NULL,39),(455,NULL,'千山区',NULL,39),(456,NULL,'台安县',NULL,39),(457,NULL,'岫岩满族自治县',NULL,39),(458,NULL,'海城市',NULL,39),(459,NULL,'新抚区',NULL,40),(460,NULL,'东洲区',NULL,40),(461,NULL,'望花区',NULL,40),(462,NULL,'顺城区',NULL,40),(464,NULL,'新宾满族自治县',NULL,40),(465,NULL,'清原满族自治县',NULL,40),(466,NULL,'平山区',NULL,41),(467,NULL,'溪湖区',NULL,41),(468,NULL,'明山区',NULL,41),(469,NULL,'南芬区',NULL,41),(470,NULL,'本溪满族自治县',NULL,41),(471,NULL,'桓仁满族自治县',NULL,41),(472,NULL,'元宝区',NULL,42),(473,NULL,'振兴区',NULL,42),(474,NULL,'振安区',NULL,42),(475,NULL,'宽甸满族自治县',NULL,42),(476,NULL,'东港市',NULL,42),(477,NULL,'凤城市',NULL,42),(478,NULL,'古塔区',NULL,43),(479,NULL,'凌河区',NULL,43),(480,NULL,'太和区',NULL,43),(481,NULL,'黑山县',NULL,43),(482,NULL,'义县',NULL,43),(483,NULL,'凌海市',NULL,43),(484,NULL,'北镇市',NULL,43),(485,NULL,'站前区',NULL,44),(486,NULL,'西市区',NULL,44),(487,NULL,'鲅鱼圈区',NULL,44),(488,NULL,'老边区',NULL,44),(489,NULL,'盖州市',NULL,44),(490,NULL,'大石桥市',NULL,44),(491,NULL,'海州区',NULL,45),(492,NULL,'新邱区',NULL,45),(493,NULL,'太平区',NULL,45),(494,NULL,'清河门区',NULL,45),(495,NULL,'细河区',NULL,45),(496,NULL,'阜新蒙古族自治县',NULL,45),(497,NULL,'彰武县',NULL,45),(498,NULL,'白塔区',NULL,46),(499,NULL,'文圣区',NULL,46),(500,NULL,'宏伟区',NULL,46),(501,NULL,'弓长岭区',NULL,46),(502,NULL,'太子河区',NULL,46),(503,NULL,'辽阳县',NULL,46),(504,NULL,'灯塔市',NULL,46),(505,NULL,'双台子区',NULL,47),(506,NULL,'兴隆台区',NULL,47),(507,NULL,'大洼县',NULL,47),(508,NULL,'盘山县',NULL,47),(509,NULL,'银州区',NULL,48),(510,NULL,'清河区',NULL,48),(511,NULL,'铁岭县',NULL,48),(512,NULL,'西丰县',NULL,48),(513,NULL,'昌图县',NULL,48),(514,NULL,'调兵山市',NULL,48),(515,NULL,'开原市',NULL,48),(516,NULL,'双塔区',NULL,49),(517,NULL,'龙城区',NULL,49),(518,NULL,'朝阳县',NULL,49),(519,NULL,'建平县',NULL,49),(520,NULL,'喀喇沁左翼蒙古族自治县',NULL,49),(521,NULL,'北票市',NULL,49),(522,NULL,'凌源市',NULL,49),(523,NULL,'连山区',NULL,50),(524,NULL,'龙港区',NULL,50),(525,NULL,'南票区',NULL,50),(526,NULL,'绥中县',NULL,50),(527,NULL,'建昌县',NULL,50),(528,NULL,'兴城市',NULL,50),(529,NULL,'南关区',NULL,51),(530,NULL,'宽城区',NULL,51),(531,NULL,'朝阳区',NULL,51),(532,NULL,'二道区',NULL,51),(533,NULL,'绿园区',NULL,51),(534,NULL,'双阳区',NULL,51),(535,NULL,'农安县',NULL,51),(536,NULL,'九台市',NULL,51),(537,NULL,'榆树市',NULL,51),(538,NULL,'德惠市',NULL,51),(539,NULL,'昌邑区',NULL,52),(540,NULL,'龙潭区',NULL,52),(541,NULL,'船营区',NULL,52),(542,NULL,'丰满区',NULL,52),(543,NULL,'永吉县',NULL,52),(544,NULL,'蛟河市',NULL,52),(545,NULL,'桦甸市',NULL,52),(546,NULL,'舒兰市',NULL,52),(547,NULL,'磐石市',NULL,52),(548,NULL,'铁西区',NULL,53),(549,NULL,'铁东区',NULL,53),(550,NULL,'梨树县',NULL,53),(551,NULL,'伊通满族自治县',NULL,53),(552,NULL,'公主岭市',NULL,53),(553,NULL,'双辽市',NULL,53),(554,NULL,'龙山区',NULL,54),(555,NULL,'西安区',NULL,54),(556,NULL,'东丰县',NULL,54),(557,NULL,'东辽县',NULL,54),(558,NULL,'东昌区',NULL,55),(559,NULL,'二道江区',NULL,55),(560,NULL,'通化县',NULL,55),(561,NULL,'辉南县',NULL,55),(562,NULL,'柳河县',NULL,55),(564,NULL,'集安市',NULL,55),(565,NULL,'浑江区',NULL,56),(566,NULL,'江源区',NULL,56),(567,NULL,'抚松县',NULL,56),(568,NULL,'长白朝鲜族自治县',NULL,56),(569,NULL,'靖宇县',NULL,56),(570,NULL,'临江市',NULL,56),(571,NULL,'宁江区',NULL,57),(572,NULL,'前郭尔罗斯蒙古族自治县',NULL,57),(573,NULL,'长岭县',NULL,57),(574,NULL,'乾安县',NULL,57),(575,NULL,'扶余县',NULL,57),(576,NULL,'洮北区',NULL,58),(577,NULL,'镇赉县',NULL,58),(578,NULL,'通榆县',NULL,58),(579,NULL,'洮南市',NULL,58),(580,NULL,'大安市',NULL,58),(581,NULL,'延吉市',NULL,59),(582,NULL,'图们市',NULL,59),(583,NULL,'敦化市',NULL,59),(584,NULL,'珲春市',NULL,59),(585,NULL,'龙井市',NULL,59),(586,NULL,'和龙市',NULL,59),(587,NULL,'汪清县',NULL,59),(588,NULL,'安图县',NULL,59),(589,NULL,'道里区',NULL,60),(590,NULL,'南岗区',NULL,60),(591,NULL,'道外区',NULL,60),(592,NULL,'香坊区',NULL,60),(594,NULL,'平房区',NULL,60),(595,NULL,'松北区',NULL,60),(596,NULL,'呼兰区',NULL,60),(597,NULL,'依兰县',NULL,60),(598,NULL,'方正县',NULL,60),(599,NULL,'宾县',NULL,60),(600,NULL,'巴彦县',NULL,60),(601,NULL,'木兰县',NULL,60),(602,NULL,'通河县',NULL,60),(603,NULL,'延寿县',NULL,60),(604,NULL,'阿城区',NULL,60),(605,NULL,'双城市',NULL,60),(606,NULL,'尚志市',NULL,60),(607,NULL,'五常市',NULL,60),(608,NULL,'龙沙区',NULL,61),(609,NULL,'建华区',NULL,61),(610,NULL,'铁锋区',NULL,61),(611,NULL,'昂昂溪区',NULL,61),(612,NULL,'富拉尔基区',NULL,61),(613,NULL,'碾子山区',NULL,61),(614,NULL,'梅里斯达斡尔族区',NULL,61),(615,NULL,'龙江县',NULL,61),(616,NULL,'依安县',NULL,61),(617,NULL,'泰来县',NULL,61),(618,NULL,'甘南县',NULL,61),(619,NULL,'富裕县',NULL,61),(620,NULL,'克山县',NULL,61),(621,NULL,'克东县',NULL,61),(622,NULL,'拜泉县',NULL,61),(623,NULL,'讷河市',NULL,61),(624,NULL,'鸡冠区',NULL,62),(625,NULL,'恒山区',NULL,62),(626,NULL,'滴道区',NULL,62),(627,NULL,'梨树区',NULL,62),(628,NULL,'城子河区',NULL,62),(629,NULL,'麻山区',NULL,62),(630,NULL,'鸡东县',NULL,62),(631,NULL,'虎林市',NULL,62),(632,NULL,'密山市',NULL,62),(633,NULL,'向阳区',NULL,63),(634,NULL,'工农区',NULL,63),(635,NULL,'南山区',NULL,63),(636,NULL,'兴安区',NULL,63),(637,NULL,'东山区',NULL,63),(638,NULL,'兴山区',NULL,63),(639,NULL,'萝北县',NULL,63),(640,NULL,'绥滨县',NULL,63),(641,NULL,'尖山区',NULL,64),(642,NULL,'岭东区',NULL,64),(643,NULL,'四方台区',NULL,64),(644,NULL,'宝山区',NULL,64),(645,NULL,'集贤县',NULL,64),(646,NULL,'友谊县',NULL,64),(647,NULL,'宝清县',NULL,64),(648,NULL,'饶河县',NULL,64),(649,NULL,'萨尔图区',NULL,65),(650,NULL,'龙凤区',NULL,65),(651,NULL,'让胡路区',NULL,65),(652,NULL,'红岗区',NULL,65),(653,NULL,'大同区',NULL,65),(654,NULL,'肇州县',NULL,65),(655,NULL,'肇源县',NULL,65),(656,NULL,'林甸县',NULL,65),(657,NULL,'杜尔伯特蒙古族自治县',NULL,65),(658,NULL,'伊春区',NULL,66),(659,NULL,'南岔区',NULL,66),(660,NULL,'友好区',NULL,66),(661,NULL,'西林区',NULL,66),(662,NULL,'翠峦区',NULL,66),(663,NULL,'新青区',NULL,66),(665,NULL,'金山屯区',NULL,66),(666,NULL,'五营区',NULL,66),(667,NULL,'乌马河区',NULL,66),(668,NULL,'汤旺河区',NULL,66),(669,NULL,'带岭区',NULL,66),(670,NULL,'乌伊岭区',NULL,66),(671,NULL,'红星区',NULL,66),(672,NULL,'上甘岭区',NULL,66),(673,NULL,'嘉荫县',NULL,66),(674,NULL,'铁力市',NULL,66),(676,NULL,'向阳区',NULL,67),(677,NULL,'前进区',NULL,67),(678,NULL,'东风区',NULL,67),(679,NULL,'郊区',NULL,67),(680,NULL,'桦南县',NULL,67),(681,NULL,'桦川县',NULL,67),(682,NULL,'汤原县',NULL,67),(683,NULL,'抚远县',NULL,67),(684,NULL,'同江市',NULL,67),(685,NULL,'富锦市',NULL,67),(686,NULL,'新兴区',NULL,68),(687,NULL,'桃山区',NULL,68),(688,NULL,'茄子河区',NULL,68),(689,NULL,'勃利县',NULL,68),(690,NULL,'东安区',NULL,69),(691,NULL,'阳明区',NULL,69),(692,NULL,'爱民区',NULL,69),(693,NULL,'西安区',NULL,69),(694,NULL,'东宁县',NULL,69),(695,NULL,'林口县',NULL,69),(696,NULL,'绥芬河市',NULL,69),(697,NULL,'海林市',NULL,69),(698,NULL,'宁安市',NULL,69),(699,NULL,'穆棱市',NULL,69),(700,NULL,'爱辉区',NULL,70),(701,NULL,'嫩江县',NULL,70),(702,NULL,'逊克县',NULL,70),(703,NULL,'孙吴县',NULL,70),(704,NULL,'北安市',NULL,70),(705,NULL,'五大连池市',NULL,70),(706,NULL,'北林区',NULL,71),(707,NULL,'望奎县',NULL,71),(708,NULL,'兰西县',NULL,71),(709,NULL,'青冈县',NULL,71),(710,NULL,'庆安县',NULL,71),(711,NULL,'明水县',NULL,71),(712,NULL,'绥棱县',NULL,71),(713,NULL,'安达市',NULL,71),(714,NULL,'肇东市',NULL,71),(715,NULL,'海伦市',NULL,71),(716,NULL,'呼玛县',NULL,72),(717,NULL,'塔河县',NULL,72),(718,NULL,'漠河县',NULL,72),(719,NULL,'黄浦区',NULL,73),(721,NULL,'徐汇区',NULL,73),(722,NULL,'长宁区',NULL,73),(723,NULL,'静安区',NULL,73),(724,NULL,'普陀区',NULL,73),(725,NULL,'闸北区',NULL,73),(726,NULL,'虹口区',NULL,73),(727,NULL,'杨浦区',NULL,73),(728,NULL,'闵行区',NULL,73),(729,NULL,'宝山区',NULL,73),(730,NULL,'嘉定区',NULL,73),(731,NULL,'浦东新区',NULL,73),(732,NULL,'金山区',NULL,73),(733,NULL,'松江区',NULL,73),(734,NULL,'青浦区',NULL,73),(736,NULL,'奉贤区',NULL,73),(737,NULL,'崇明县',NULL,73),(738,NULL,'玄武区',NULL,74),(739,NULL,'白下区',NULL,74),(740,NULL,'秦淮区',NULL,74),(741,NULL,'建邺区',NULL,74),(742,NULL,'鼓楼区',NULL,74),(743,NULL,'下关区',NULL,74),(744,NULL,'浦口区',NULL,74),(745,NULL,'栖霞区',NULL,74),(746,NULL,'雨花台区',NULL,74),(747,NULL,'江宁区',NULL,74),(748,NULL,'六合区',NULL,74),(749,NULL,'溧水县',NULL,74),(750,NULL,'高淳县',NULL,74),(751,NULL,'崇安区',NULL,75),(752,NULL,'南长区',NULL,75),(753,NULL,'北塘区',NULL,75),(754,NULL,'锡山区',NULL,75),(755,NULL,'惠山区',NULL,75),(756,NULL,'滨湖区',NULL,75),(757,NULL,'江阴市',NULL,75),(758,NULL,'宜兴市',NULL,75),(759,NULL,'鼓楼区',NULL,76),(760,NULL,'云龙区',NULL,76),(762,NULL,'贾汪区',NULL,76),(763,NULL,'泉山区',NULL,76),(764,NULL,'丰县',NULL,76),(765,NULL,'沛县',NULL,76),(766,NULL,'铜山区',NULL,76),(767,NULL,'睢宁县',NULL,76),(769,NULL,'邳州市',NULL,76),(770,NULL,'天宁区',NULL,77),(771,NULL,'钟楼区',NULL,77),(772,NULL,'戚墅堰区',NULL,77),(773,NULL,'新北区',NULL,77),(774,NULL,'武进区',NULL,77),(775,NULL,'溧阳市',NULL,77),(776,NULL,'金坛市',NULL,77),(779,NULL,'姑苏区',NULL,78),(780,NULL,'虎丘区',NULL,78),(781,NULL,'吴中区',NULL,78),(782,NULL,'相城区',NULL,78),(783,NULL,'常熟市',NULL,78),(784,NULL,'张家港市',NULL,78),(785,NULL,'昆山市',NULL,78),(786,NULL,'吴江区',NULL,78),(787,NULL,'太仓市',NULL,78),(788,NULL,'崇川区',NULL,79),(789,NULL,'港闸区',NULL,79),(790,NULL,'海安县',NULL,79),(791,NULL,'如东县',NULL,79),(792,NULL,'启东市',NULL,79),(793,NULL,'如皋市',NULL,79),(794,NULL,'通州区',NULL,79),(795,NULL,'海门市',NULL,79),(796,NULL,'连云区',NULL,80),(797,NULL,'新浦区',NULL,80),(798,NULL,'海州区',NULL,80),(799,NULL,'赣榆县',NULL,80),(800,NULL,'东海县',NULL,80),(801,NULL,'灌云县',NULL,80),(802,NULL,'灌南县',NULL,80),(803,NULL,'清河区',NULL,81),(804,NULL,'楚州区',NULL,81),(805,NULL,'淮阴区',NULL,81),(806,NULL,'清浦区',NULL,81),(807,NULL,'涟水县',NULL,81),(808,NULL,'洪泽县',NULL,81),(809,NULL,'盱眙县',NULL,81),(810,NULL,'金湖县',NULL,81),(811,NULL,'亭湖区',NULL,82),(812,NULL,'盐都区',NULL,82),(813,NULL,'响水县',NULL,82),(814,NULL,'滨海县',NULL,82),(815,NULL,'阜宁县',NULL,82),(816,NULL,'射阳县',NULL,82),(817,NULL,'建湖县',NULL,82),(818,NULL,'东台市',NULL,82),(819,NULL,'大丰市',NULL,82),(820,NULL,'广陵区',NULL,83),(821,NULL,'邗江区',NULL,83),(822,NULL,'江都区',NULL,83),(823,NULL,'宝应县',NULL,83),(824,NULL,'仪征市',NULL,83),(825,NULL,'高邮市',NULL,83),(826,NULL,'江都市',NULL,83),(827,NULL,'京口区',NULL,84),(828,NULL,'润州区',NULL,84),(829,NULL,'丹徒区',NULL,84),(830,NULL,'丹阳市',NULL,84),(831,NULL,'扬中市',NULL,84),(832,NULL,'句容市',NULL,84),(833,NULL,'海陵区',NULL,85),(834,NULL,'高港区',NULL,85),(835,NULL,'兴化市',NULL,85),(836,NULL,'靖江市',NULL,85),(837,NULL,'泰兴市',NULL,85),(838,NULL,'姜堰市',NULL,85),(839,NULL,'宿城区',NULL,86),(840,NULL,'宿豫区',NULL,86),(841,NULL,'沭阳县',NULL,86),(842,NULL,'泗阳县',NULL,86),(843,NULL,'泗洪县',NULL,86),(844,NULL,'上城区',NULL,87),(845,NULL,'下城区',NULL,87),(846,NULL,'江干区',NULL,87),(847,NULL,'拱墅区',NULL,87),(848,NULL,'西湖区',NULL,87),(849,NULL,'滨江区',NULL,87),(850,NULL,'萧山区',NULL,87),(851,NULL,'余杭区',NULL,87),(852,NULL,'桐庐县',NULL,87),(853,NULL,'淳安县',NULL,87),(854,NULL,'建德市',NULL,87),(855,NULL,'富阳市',NULL,87),(856,NULL,'临安市',NULL,87),(857,NULL,'海曙区',NULL,88),(858,NULL,'江东区',NULL,88),(859,NULL,'江北区',NULL,88),(860,NULL,'北仑区',NULL,88),(861,NULL,'镇海区',NULL,88),(862,NULL,'鄞州区',NULL,88),(863,NULL,'象山县',NULL,88),(864,NULL,'宁海县',NULL,88),(865,NULL,'余姚市',NULL,88),(866,NULL,'慈溪市',NULL,88),(867,NULL,'奉化市',NULL,88),(868,NULL,'鹿城区',NULL,89),(869,NULL,'龙湾区',NULL,89),(871,NULL,'洞头县',NULL,89),(872,NULL,'永嘉县',NULL,89),(873,NULL,'平阳县',NULL,89),(874,NULL,'苍南县',NULL,89),(875,NULL,'文成县',NULL,89),(876,NULL,'泰顺县',NULL,89),(877,NULL,'瑞安市',NULL,89),(878,NULL,'乐清市',NULL,89),(879,NULL,'南湖区',NULL,90),(880,NULL,'秀洲区',NULL,90),(881,NULL,'嘉善县',NULL,90),(882,NULL,'海盐县',NULL,90),(883,NULL,'海宁市',NULL,90),(884,NULL,'平湖市',NULL,90),(885,NULL,'桐乡市',NULL,90),(886,NULL,'吴兴区',NULL,91),(887,NULL,'南浔区',NULL,91),(888,NULL,'德清县',NULL,91),(889,NULL,'长兴县',NULL,91),(890,NULL,'安吉县',NULL,91),(891,NULL,'越城区',NULL,92),(892,NULL,'绍兴县',NULL,92),(893,NULL,'新昌县',NULL,92),(894,NULL,'诸暨市',NULL,92),(895,NULL,'上虞市',NULL,92),(896,NULL,'嵊州市',NULL,92),(897,NULL,'婺城区',NULL,93),(898,NULL,'金东区',NULL,93),(899,NULL,'武义县',NULL,93),(900,NULL,'浦江县',NULL,93),(901,NULL,'磐安县',NULL,93),(902,NULL,'兰溪市',NULL,93),(903,NULL,'义乌市',NULL,93),(904,NULL,'东阳市',NULL,93),(905,NULL,'永康市',NULL,93),(906,NULL,'柯城区',NULL,94),(907,NULL,'衢江区',NULL,94),(908,NULL,'常山县',NULL,94),(909,NULL,'开化县',NULL,94),(910,NULL,'龙游县',NULL,94),(911,NULL,'江山市',NULL,94),(912,NULL,'定海区',NULL,95),(913,NULL,'普陀区',NULL,95),(914,NULL,'岱山县',NULL,95),(915,NULL,'嵊泗县',NULL,95),(916,NULL,'椒江区',NULL,96),(917,NULL,'黄岩区',NULL,96),(918,NULL,'路桥区',NULL,96),(919,NULL,'玉环县',NULL,96),(920,NULL,'三门县',NULL,96),(921,NULL,'天台县',NULL,96),(922,NULL,'仙居县',NULL,96),(923,NULL,'温岭市',NULL,96),(924,NULL,'临海市',NULL,96),(925,NULL,'莲都区',NULL,97),(926,NULL,'青田县',NULL,97),(927,NULL,'缙云县',NULL,97),(928,NULL,'遂昌县',NULL,97),(929,NULL,'松阳县',NULL,97),(930,NULL,'云和县',NULL,97),(931,NULL,'庆元县',NULL,97),(932,NULL,'景宁畲族自治县',NULL,97),(933,NULL,'龙泉市',NULL,97),(934,NULL,'瑶海区',NULL,98),(935,NULL,'庐阳区',NULL,98),(936,NULL,'蜀山区',NULL,98),(937,NULL,'包河区',NULL,98),(938,NULL,'长丰县',NULL,98),(939,NULL,'肥东县',NULL,98),(940,NULL,'肥西县',NULL,98),(941,NULL,'镜湖区',NULL,99),(942,NULL,'弋江区',NULL,99),(943,NULL,'鸠江区',NULL,99),(944,NULL,'三山区',NULL,99),(945,NULL,'芜湖县',NULL,99),(946,NULL,'繁昌县',NULL,99),(947,NULL,'南陵县',NULL,99),(948,NULL,'龙子湖区',NULL,100),(949,NULL,'蚌山区',NULL,100),(950,NULL,'禹会区',NULL,100),(951,NULL,'淮上区',NULL,100),(952,NULL,'怀远县',NULL,100),(953,NULL,'五河县',NULL,100),(954,NULL,'固镇县',NULL,100),(955,NULL,'大通区',NULL,101),(956,NULL,'田家庵区',NULL,101),(957,NULL,'谢家集区',NULL,101),(958,NULL,'八公山区',NULL,101),(959,NULL,'潘集区',NULL,101),(960,NULL,'凤台县',NULL,101),(962,NULL,'花山区',NULL,102),(963,NULL,'雨山区',NULL,102),(964,NULL,'博望区',NULL,102),(965,NULL,'杜集区',NULL,103),(966,NULL,'相山区',NULL,103),(967,NULL,'烈山区',NULL,103),(968,NULL,'濉溪县',NULL,103),(969,NULL,'铜官山区',NULL,104),(970,NULL,'狮子山区',NULL,104),(972,NULL,'铜陵县',NULL,104),(973,NULL,'迎江区',NULL,105),(974,NULL,'大观区',NULL,105),(975,NULL,'宜秀区',NULL,105),(976,NULL,'怀宁县',NULL,105),(977,NULL,'枞阳县',NULL,105),(978,NULL,'潜山县',NULL,105),(979,NULL,'太湖县',NULL,105),(980,NULL,'宿松县',NULL,105),(981,NULL,'望江县',NULL,105),(982,NULL,'岳西县',NULL,105),(983,NULL,'桐城市',NULL,105),(984,NULL,'屯溪区',NULL,106),(985,NULL,'黄山区',NULL,106),(986,NULL,'徽州区',NULL,106),(987,NULL,'歙县',NULL,106),(988,NULL,'休宁县',NULL,106),(989,NULL,'黟县',NULL,106),(990,NULL,'祁门县',NULL,106),(991,NULL,'琅琊区',NULL,107),(992,NULL,'南谯区',NULL,107),(993,NULL,'来安县',NULL,107),(994,NULL,'全椒县',NULL,107),(995,NULL,'定远县',NULL,107),(996,NULL,'凤阳县',NULL,107),(997,NULL,'天长市',NULL,107),(998,NULL,'明光市',NULL,107),(999,NULL,'颍州区',NULL,108),(1000,NULL,'颍东区',NULL,108),(1001,NULL,'颍泉区',NULL,108),(1002,NULL,'临泉县',NULL,108),(1003,NULL,'太和县',NULL,108),(1004,NULL,'阜南县',NULL,108),(1005,NULL,'颍上县',NULL,108),(1006,NULL,'界首市',NULL,108),(1007,NULL,'埇桥区',NULL,109),(1008,NULL,'砀山县',NULL,109),(1009,NULL,'萧县',NULL,109),(1010,NULL,'灵璧县',NULL,109),(1011,NULL,'泗县',NULL,109),(1017,NULL,'金安区',NULL,111),(1018,NULL,'裕安区',NULL,111),(1019,NULL,'寿县',NULL,111),(1020,NULL,'霍邱县',NULL,111),(1021,NULL,'舒城县',NULL,111),(1022,NULL,'金寨县',NULL,111),(1023,NULL,'霍山县',NULL,111),(1024,NULL,'谯城区',NULL,112),(1025,NULL,'涡阳县',NULL,112),(1026,NULL,'蒙城县',NULL,112),(1027,NULL,'利辛县',NULL,112),(1028,NULL,'贵池区',NULL,113),(1029,NULL,'东至县',NULL,113),(1030,NULL,'石台县',NULL,113),(1031,NULL,'青阳县',NULL,113),(1032,NULL,'宣州区',NULL,114),(1033,NULL,'郎溪县',NULL,114),(1034,NULL,'广德县',NULL,114),(1035,NULL,'泾县',NULL,114),(1036,NULL,'绩溪县',NULL,114),(1037,NULL,'旌德县',NULL,114),(1038,NULL,'宁国市',NULL,114),(1039,NULL,'鼓楼区',NULL,115),(1040,NULL,'台江区',NULL,115),(1041,NULL,'仓山区',NULL,115),(1042,NULL,'马尾区',NULL,115),(1043,NULL,'晋安区',NULL,115),(1044,NULL,'闽侯县',NULL,115),(1045,NULL,'连江县',NULL,115),(1046,NULL,'罗源县',NULL,115),(1047,NULL,'闽清县',NULL,115),(1048,NULL,'永泰县',NULL,115),(1049,NULL,'平潭县',NULL,115),(1050,NULL,'福清市',NULL,115),(1051,NULL,'长乐市',NULL,115),(1052,NULL,'思明区',NULL,116),(1053,NULL,'海沧区',NULL,116),(1054,NULL,'湖里区',NULL,116),(1055,NULL,'集美区',NULL,116),(1056,NULL,'同安区',NULL,116),(1057,NULL,'翔安区',NULL,116),(1058,NULL,'城厢区',NULL,117),(1059,NULL,'涵江区',NULL,117),(1060,NULL,'荔城区',NULL,117),(1061,NULL,'秀屿区',NULL,117),(1062,NULL,'仙游县',NULL,117),(1063,NULL,'梅列区',NULL,118),(1064,NULL,'三元区',NULL,118),(1065,NULL,'明溪县',NULL,118),(1066,NULL,'清流县',NULL,118),(1067,NULL,'宁化县',NULL,118),(1068,NULL,'大田县',NULL,118),(1069,NULL,'尤溪县',NULL,118),(1070,NULL,'沙县',NULL,118),(1071,NULL,'将乐县',NULL,118),(1072,NULL,'泰宁县',NULL,118),(1073,NULL,'建宁县',NULL,118),(1074,NULL,'永安市',NULL,118),(1075,NULL,'鲤城区',NULL,119),(1077,NULL,'洛江区',NULL,119),(1078,NULL,'泉港区',NULL,119),(1079,NULL,'惠安县',NULL,119),(1080,NULL,'安溪县',NULL,119),(1081,NULL,'永春县',NULL,119),(1082,NULL,'德化县',NULL,119),(1083,NULL,'金门县',NULL,119),(1084,NULL,'石狮市',NULL,119),(1085,NULL,'晋江市',NULL,119),(1086,NULL,'南安市',NULL,119),(1087,NULL,'芗城区',NULL,120),(1088,NULL,'龙文区',NULL,120),(1089,NULL,'云霄县',NULL,120),(1090,NULL,'漳浦县',NULL,120),(1091,NULL,'诏安县',NULL,120),(1092,NULL,'长泰县',NULL,120),(1093,NULL,'东山县',NULL,120),(1094,NULL,'南靖县',NULL,120),(1095,NULL,'平和县',NULL,120),(1096,NULL,'华安县',NULL,120),(1097,NULL,'龙海市',NULL,120),(1098,NULL,'延平区',NULL,121),(1099,NULL,'顺昌县',NULL,121),(1100,NULL,'浦城县',NULL,121),(1101,NULL,'光泽县',NULL,121),(1102,NULL,'松溪县',NULL,121),(1103,NULL,'政和县',NULL,121),(1104,NULL,'邵武市',NULL,121),(1105,NULL,'武夷山市',NULL,121),(1106,NULL,'建瓯市',NULL,121),(1107,NULL,'建阳市',NULL,121),(1108,NULL,'新罗区',NULL,122),(1109,NULL,'长汀县',NULL,122),(1110,NULL,'永定县',NULL,122),(1111,NULL,'上杭县',NULL,122),(1112,NULL,'武平县',NULL,122),(1113,NULL,'连城县',NULL,122),(1114,NULL,'漳平市',NULL,122),(1115,NULL,'蕉城区',NULL,123),(1116,NULL,'霞浦县',NULL,123),(1117,NULL,'古田县',NULL,123),(1118,NULL,'屏南县',NULL,123),(1119,NULL,'寿宁县',NULL,123),(1120,NULL,'周宁县',NULL,123),(1121,NULL,'柘荣县',NULL,123),(1122,NULL,'福安市',NULL,123),(1123,NULL,'福鼎市',NULL,123),(1124,NULL,'东湖区',NULL,124),(1125,NULL,'西湖区',NULL,124),(1126,NULL,'青云谱区',NULL,124),(1127,NULL,'湾里区',NULL,124),(1128,NULL,'青山湖区',NULL,124),(1129,NULL,'南昌县',NULL,124),(1130,NULL,'新建县',NULL,124),(1131,NULL,'安义县',NULL,124),(1132,NULL,'进贤县',NULL,124),(1133,NULL,'昌江区',NULL,125),(1134,NULL,'珠山区',NULL,125),(1135,NULL,'浮梁县',NULL,125),(1136,NULL,'乐平市',NULL,125),(1137,NULL,'安源区',NULL,126),(1138,NULL,'湘东区',NULL,126),(1139,NULL,'莲花县',NULL,126),(1140,NULL,'上栗县',NULL,126),(1141,NULL,'芦溪县',NULL,126),(1142,NULL,'庐山区',NULL,127),(1143,NULL,'浔阳区',NULL,127),(1144,NULL,'九江县',NULL,127),(1145,NULL,'武宁县',NULL,127),(1146,NULL,'修水县',NULL,127),(1147,NULL,'永修县',NULL,127),(1148,NULL,'德安县',NULL,127),(1149,NULL,'星子县',NULL,127),(1150,NULL,'都昌县',NULL,127),(1151,NULL,'湖口县',NULL,127),(1152,NULL,'彭泽县',NULL,127),(1153,NULL,'瑞昌市',NULL,127),(1154,NULL,'渝水区',NULL,128),(1155,NULL,'分宜县',NULL,128),(1156,NULL,'月湖区',NULL,129),(1157,NULL,'余江县',NULL,129),(1158,NULL,'贵溪市',NULL,129),(1159,NULL,'章贡区',NULL,130),(1160,NULL,'赣县',NULL,130),(1161,NULL,'信丰县',NULL,130),(1162,NULL,'大余县',NULL,130),(1163,NULL,'上犹县',NULL,130),(1164,NULL,'崇义县',NULL,130),(1165,NULL,'安远县',NULL,130),(1166,NULL,'龙南县',NULL,130),(1167,NULL,'定南县',NULL,130),(1168,NULL,'全南县',NULL,130),(1169,NULL,'宁都县',NULL,130),(1170,NULL,'于都县',NULL,130),(1171,NULL,'兴国县',NULL,130),(1172,NULL,'会昌县',NULL,130),(1173,NULL,'寻乌县',NULL,130),(1174,NULL,'石城县',NULL,130),(1175,NULL,'瑞金市',NULL,130),(1177,NULL,'吉州区',NULL,131),(1178,NULL,'青原区',NULL,131),(1179,NULL,'吉安县',NULL,131),(1180,NULL,'吉水县',NULL,131),(1181,NULL,'峡江县',NULL,131),(1182,NULL,'新干县',NULL,131),(1183,NULL,'永丰县',NULL,131),(1184,NULL,'泰和县',NULL,131),(1185,NULL,'遂川县',NULL,131),(1186,NULL,'万安县',NULL,131),(1187,NULL,'安福县',NULL,131),(1188,NULL,'永新县',NULL,131),(1189,NULL,'井冈山市',NULL,131),(1190,NULL,'袁州区',NULL,132),(1191,NULL,'奉新县',NULL,132),(1192,NULL,'万载县',NULL,132),(1193,NULL,'上高县',NULL,132),(1194,NULL,'宜丰县',NULL,132),(1195,NULL,'靖安县',NULL,132),(1196,NULL,'铜鼓县',NULL,132),(1197,NULL,'丰城市',NULL,132),(1198,NULL,'樟树市',NULL,132),(1199,NULL,'高安市',NULL,132),(1200,NULL,'临川区',NULL,133),(1201,NULL,'南城县',NULL,133),(1202,NULL,'黎川县',NULL,133),(1203,NULL,'南丰县',NULL,133),(1204,NULL,'崇仁县',NULL,133),(1205,NULL,'乐安县',NULL,133),(1206,NULL,'宜黄县',NULL,133),(1207,NULL,'金溪县',NULL,133),(1208,NULL,'资溪县',NULL,133),(1209,NULL,'东乡县',NULL,133),(1210,NULL,'广昌县',NULL,133),(1211,NULL,'信州区',NULL,134),(1212,NULL,'上饶县',NULL,134),(1213,NULL,'广丰县',NULL,134),(1214,NULL,'玉山县',NULL,134),(1215,NULL,'铅山县',NULL,134),(1216,NULL,'横峰县',NULL,134),(1217,NULL,'弋阳县',NULL,134),(1218,NULL,'余干县',NULL,134),(1219,NULL,'鄱阳县',NULL,134),(1220,NULL,'万年县',NULL,134),(1221,NULL,'婺源县',NULL,134),(1222,NULL,'德兴市',NULL,134),(1223,NULL,'历下区',NULL,135),(1224,NULL,'市中区',NULL,135),(1225,NULL,'槐荫区',NULL,135),(1226,NULL,'天桥区',NULL,135),(1227,NULL,'历城区',NULL,135),(1228,NULL,'长清区',NULL,135),(1229,NULL,'平阴县',NULL,135),(1230,NULL,'济阳县',NULL,135),(1231,NULL,'商河县',NULL,135),(1232,NULL,'章丘市',NULL,135),(1233,NULL,'市南区',NULL,136),(1234,NULL,'市北区',NULL,136),(1235,NULL,'四方区',NULL,136),(1236,NULL,'黄岛区',NULL,136),(1237,NULL,'崂山区',NULL,136),(1238,NULL,'李沧区',NULL,136),(1239,NULL,'城阳区',NULL,136),(1240,NULL,'胶州市',NULL,136),(1241,NULL,'即墨市',NULL,136),(1242,NULL,'平度市',NULL,136),(1243,NULL,'胶南市',NULL,136),(1244,NULL,'莱西市',NULL,136),(1245,NULL,'淄川区',NULL,137),(1246,NULL,'张店区',NULL,137),(1247,NULL,'博山区',NULL,137),(1248,NULL,'临淄区',NULL,137),(1249,NULL,'周村区',NULL,137),(1250,NULL,'桓台县',NULL,137),(1251,NULL,'高青县',NULL,137),(1252,NULL,'沂源县',NULL,137),(1253,NULL,'市中区',NULL,138),(1254,NULL,'薛城区',NULL,138),(1255,NULL,'峄城区',NULL,138),(1256,NULL,'台儿庄区',NULL,138),(1257,NULL,'山亭区',NULL,138),(1258,NULL,'滕州市',NULL,138),(1259,NULL,'东营区',NULL,139),(1260,NULL,'河口区',NULL,139),(1261,NULL,'垦利县',NULL,139),(1262,NULL,'利津县',NULL,139),(1263,NULL,'广饶县',NULL,139),(1264,NULL,'芝罘区',NULL,140),(1265,NULL,'福山区',NULL,140),(1266,NULL,'牟平区',NULL,140),(1267,NULL,'莱山区',NULL,140),(1268,NULL,'长岛县',NULL,140),(1269,NULL,'龙口市',NULL,140),(1270,NULL,'莱阳市',NULL,140),(1271,NULL,'莱州市',NULL,140),(1272,NULL,'蓬莱市',NULL,140),(1273,NULL,'招远市',NULL,140),(1274,NULL,'栖霞市',NULL,140),(1275,NULL,'海阳市',NULL,140),(1277,NULL,'寒亭区',NULL,141),(1278,NULL,'坊子区',NULL,141),(1279,NULL,'奎文区',NULL,141),(1280,NULL,'临朐县',NULL,141),(1281,NULL,'昌乐县',NULL,141),(1282,NULL,'青州市',NULL,141),(1283,NULL,'诸城市',NULL,141),(1284,NULL,'寿光市',NULL,141),(1285,NULL,'安丘市',NULL,141),(1286,NULL,'高密市',NULL,141),(1287,NULL,'昌邑市',NULL,141),(1288,NULL,'市中区',NULL,142),(1289,NULL,'任城区',NULL,142),(1290,NULL,'微山县',NULL,142),(1291,NULL,'鱼台县',NULL,142),(1292,NULL,'金乡县',NULL,142),(1293,NULL,'嘉祥县',NULL,142),(1294,NULL,'汶上县',NULL,142),(1295,NULL,'泗水县',NULL,142),(1296,NULL,'梁山县',NULL,142),(1297,NULL,'曲阜市',NULL,142),(1298,NULL,'兖州市',NULL,142),(1299,NULL,'邹城市',NULL,142),(1300,NULL,'泰山区',NULL,143),(1301,NULL,'岱岳区',NULL,143),(1302,NULL,'宁阳县',NULL,143),(1303,NULL,'东平县',NULL,143),(1304,NULL,'新泰市',NULL,143),(1305,NULL,'肥城市',NULL,143),(1306,NULL,'环翠区',NULL,144),(1307,NULL,'文登市',NULL,144),(1308,NULL,'荣成市',NULL,144),(1309,NULL,'乳山市',NULL,144),(1310,NULL,'东港区',NULL,145),(1311,NULL,'岚山区',NULL,145),(1312,NULL,'五莲县',NULL,145),(1313,NULL,'莒县',NULL,145),(1314,NULL,'莱城区',NULL,146),(1315,NULL,'钢城区',NULL,146),(1316,NULL,'兰山区',NULL,147),(1317,NULL,'罗庄区',NULL,147),(1318,NULL,'河东区',NULL,147),(1319,NULL,'沂南县',NULL,147),(1320,NULL,'郯城县',NULL,147),(1321,NULL,'沂水县',NULL,147),(1322,NULL,'苍山县',NULL,147),(1323,NULL,'费县',NULL,147),(1324,NULL,'平邑县',NULL,147),(1325,NULL,'莒南县',NULL,147),(1326,NULL,'蒙阴县',NULL,147),(1327,NULL,'临沭县',NULL,147),(1328,NULL,'德城区',NULL,148),(1329,NULL,'陵县',NULL,148),(1330,NULL,'宁津县',NULL,148),(1331,NULL,'庆云县',NULL,148),(1332,NULL,'临邑县',NULL,148),(1333,NULL,'齐河县',NULL,148),(1334,NULL,'平原县',NULL,148),(1335,NULL,'夏津县',NULL,148),(1336,NULL,'武城县',NULL,148),(1337,NULL,'乐陵市',NULL,148),(1338,NULL,'禹城市',NULL,148),(1339,NULL,'东昌府区',NULL,149),(1340,NULL,'阳谷县',NULL,149),(1341,NULL,'莘县',NULL,149),(1342,NULL,'茌平县',NULL,149),(1343,NULL,'东阿县',NULL,149),(1344,NULL,'冠县',NULL,149),(1345,NULL,'高唐县',NULL,149),(1346,NULL,'临清市',NULL,149),(1347,NULL,'滨城区',NULL,150),(1348,NULL,'惠民县',NULL,150),(1349,NULL,'阳信县',NULL,150),(1350,NULL,'无棣县',NULL,150),(1351,NULL,'沾化县',NULL,150),(1352,NULL,'博兴县',NULL,150),(1353,NULL,'邹平县',NULL,150),(1354,NULL,'牡丹区',NULL,151),(1355,NULL,'曹县',NULL,151),(1356,NULL,'单县',NULL,151),(1357,NULL,'成武县',NULL,151),(1358,NULL,'巨野县',NULL,151),(1359,NULL,'郓城县',NULL,151),(1360,NULL,'鄄城县',NULL,151),(1361,NULL,'定陶县',NULL,151),(1362,NULL,'东明县',NULL,151),(1363,NULL,'中原区',NULL,152),(1364,NULL,'二七区',NULL,152),(1365,NULL,'管城回族区',NULL,152),(1366,NULL,'金水区',NULL,152),(1367,NULL,'上街区',NULL,152),(1368,NULL,'惠济区',NULL,152),(1369,NULL,'中牟县',NULL,152),(1370,NULL,'巩义市',NULL,152),(1371,NULL,'荥阳市',NULL,152),(1372,NULL,'新密市',NULL,152),(1373,NULL,'新郑市',NULL,152),(1374,NULL,'登封市',NULL,152),(1375,NULL,'龙亭区',NULL,153),(1377,NULL,'鼓楼区',NULL,153),(1378,NULL,'禹王台区',NULL,153),(1379,NULL,'金明区',NULL,153),(1380,NULL,'杞县',NULL,153),(1381,NULL,'通许县',NULL,153),(1382,NULL,'尉氏县',NULL,153),(1383,NULL,'开封县',NULL,153),(1384,NULL,'兰考县',NULL,153),(1385,NULL,'老城区',NULL,154),(1386,NULL,'西工区',NULL,154),(1387,NULL,'廛河回族区',NULL,154),(1388,NULL,'涧西区',NULL,154),(1389,NULL,'吉利区',NULL,154),(1390,NULL,'洛龙区',NULL,154),(1391,NULL,'孟津县',NULL,154),(1392,NULL,'新安县',NULL,154),(1393,NULL,'栾川县',NULL,154),(1394,NULL,'嵩县',NULL,154),(1395,NULL,'汝阳县',NULL,154),(1396,NULL,'宜阳县',NULL,154),(1397,NULL,'洛宁县',NULL,154),(1398,NULL,'伊川县',NULL,154),(1399,NULL,'偃师市',NULL,154),(1400,NULL,'新华区',NULL,155),(1401,NULL,'卫东区',NULL,155),(1402,NULL,'石龙区',NULL,155),(1403,NULL,'湛河区',NULL,155),(1404,NULL,'宝丰县',NULL,155),(1405,NULL,'叶县',NULL,155),(1406,NULL,'鲁山县',NULL,155),(1407,NULL,'郏县',NULL,155),(1408,NULL,'舞钢市',NULL,155),(1409,NULL,'汝州市',NULL,155),(1410,NULL,'文峰区',NULL,156),(1411,NULL,'北关区',NULL,156),(1412,NULL,'殷都区',NULL,156),(1413,NULL,'龙安区',NULL,156),(1414,NULL,'安阳县',NULL,156),(1415,NULL,'汤阴县',NULL,156),(1416,NULL,'滑县',NULL,156),(1417,NULL,'内黄县',NULL,156),(1418,NULL,'林州市',NULL,156),(1419,NULL,'鹤山区',NULL,157),(1420,NULL,'山城区',NULL,157),(1421,NULL,'淇滨区',NULL,157),(1422,NULL,'浚县',NULL,157),(1423,NULL,'淇县',NULL,157),(1424,NULL,'红旗区',NULL,158),(1425,NULL,'卫滨区',NULL,158),(1426,NULL,'凤泉区',NULL,158),(1427,NULL,'牧野区',NULL,158),(1428,NULL,'新乡县',NULL,158),(1429,NULL,'获嘉县',NULL,158),(1430,NULL,'原阳县',NULL,158),(1431,NULL,'延津县',NULL,158),(1432,NULL,'封丘县',NULL,158),(1433,NULL,'长垣县',NULL,158),(1434,NULL,'卫辉市',NULL,158),(1435,NULL,'辉县市',NULL,158),(1436,NULL,'解放区',NULL,159),(1437,NULL,'中站区',NULL,159),(1438,NULL,'马村区',NULL,159),(1439,NULL,'山阳区',NULL,159),(1440,NULL,'修武县',NULL,159),(1441,NULL,'博爱县',NULL,159),(1442,NULL,'武陟县',NULL,159),(1443,NULL,'温县',NULL,159),(1445,NULL,'沁阳市',NULL,159),(1446,NULL,'孟州市',NULL,159),(1447,NULL,'华龙区',NULL,160),(1448,NULL,'清丰县',NULL,160),(1449,NULL,'南乐县',NULL,160),(1450,NULL,'范县',NULL,160),(1451,NULL,'台前县',NULL,160),(1452,NULL,'濮阳县',NULL,160),(1453,NULL,'魏都区',NULL,161),(1454,NULL,'许昌县',NULL,161),(1455,NULL,'鄢陵县',NULL,161),(1456,NULL,'襄城县',NULL,161),(1457,NULL,'禹州市',NULL,161),(1458,NULL,'长葛市',NULL,161),(1459,NULL,'源汇区',NULL,162),(1460,NULL,'郾城区',NULL,162),(1461,NULL,'召陵区',NULL,162),(1462,NULL,'舞阳县',NULL,162),(1463,NULL,'临颍县',NULL,162),(1464,NULL,'市辖区',NULL,163),(1465,NULL,'湖滨区',NULL,163),(1466,NULL,'渑池县',NULL,163),(1467,NULL,'陕县',NULL,163),(1468,NULL,'卢氏县',NULL,163),(1469,NULL,'义马市',NULL,163),(1470,NULL,'灵宝市',NULL,163),(1471,NULL,'宛城区',NULL,164),(1472,NULL,'卧龙区',NULL,164),(1473,NULL,'南召县',NULL,164),(1474,NULL,'方城县',NULL,164),(1475,NULL,'西峡县',NULL,164),(1476,NULL,'镇平县',NULL,164),(1478,NULL,'淅川县',NULL,164),(1479,NULL,'社旗县',NULL,164),(1480,NULL,'唐河县',NULL,164),(1481,NULL,'新野县',NULL,164),(1482,NULL,'桐柏县',NULL,164),(1483,NULL,'邓州市',NULL,164),(1484,NULL,'梁园区',NULL,165),(1485,NULL,'睢阳区',NULL,165),(1486,NULL,'民权县',NULL,165),(1487,NULL,'睢县',NULL,165),(1488,NULL,'宁陵县',NULL,165),(1489,NULL,'柘城县',NULL,165),(1490,NULL,'虞城县',NULL,165),(1491,NULL,'夏邑县',NULL,165),(1492,NULL,'永城市',NULL,165),(1493,NULL,'浉河区',NULL,166),(1494,NULL,'平桥区',NULL,166),(1495,NULL,'罗山县',NULL,166),(1496,NULL,'光山县',NULL,166),(1497,NULL,'新县',NULL,166),(1498,NULL,'商城县',NULL,166),(1499,NULL,'固始县',NULL,166),(1500,NULL,'潢川县',NULL,166),(1501,NULL,'淮滨县',NULL,166),(1502,NULL,'息县',NULL,166),(1503,NULL,'川汇区',NULL,167),(1504,NULL,'扶沟县',NULL,167),(1505,NULL,'西华县',NULL,167),(1506,NULL,'商水县',NULL,167),(1507,NULL,'沈丘县',NULL,167),(1508,NULL,'郸城县',NULL,167),(1509,NULL,'淮阳县',NULL,167),(1510,NULL,'太康县',NULL,167),(1511,NULL,'鹿邑县',NULL,167),(1512,NULL,'项城市',NULL,167),(1513,NULL,'驿城区',NULL,168),(1514,NULL,'西平县',NULL,168),(1515,NULL,'上蔡县',NULL,168),(1516,NULL,'平舆县',NULL,168),(1517,NULL,'正阳县',NULL,168),(1518,NULL,'确山县',NULL,168),(1519,NULL,'泌阳县',NULL,168),(1520,NULL,'汝南县',NULL,168),(1521,NULL,'遂平县',NULL,168),(1522,NULL,'新蔡县',NULL,168),(1523,NULL,'江岸区',NULL,169),(1524,NULL,'江汉区',NULL,169),(1525,NULL,'硚口区',NULL,169),(1526,NULL,'汉阳区',NULL,169),(1527,NULL,'武昌区',NULL,169),(1528,NULL,'青山区',NULL,169),(1529,NULL,'洪山区',NULL,169),(1530,NULL,'东西湖区',NULL,169),(1531,NULL,'汉南区',NULL,169),(1532,NULL,'蔡甸区',NULL,169),(1533,NULL,'江夏区',NULL,169),(1534,NULL,'黄陂区',NULL,169),(1535,NULL,'新洲区',NULL,169),(1536,NULL,'黄石港区',NULL,170),(1537,NULL,'西塞山区',NULL,170),(1538,NULL,'下陆区',NULL,170),(1539,NULL,'铁山区',NULL,170),(1540,NULL,'阳新县',NULL,170),(1541,NULL,'大冶市',NULL,170),(1542,NULL,'茅箭区',NULL,171),(1543,NULL,'张湾区',NULL,171),(1544,NULL,'郧县',NULL,171),(1545,NULL,'郧西县',NULL,171),(1546,NULL,'竹山县',NULL,171),(1547,NULL,'竹溪县',NULL,171),(1548,NULL,'房县',NULL,171),(1549,NULL,'丹江口市',NULL,171),(1550,NULL,'西陵区',NULL,172),(1551,NULL,'伍家岗区',NULL,172),(1552,NULL,'点军区',NULL,172),(1553,NULL,'猇亭区',NULL,172),(1554,NULL,'夷陵区',NULL,172),(1555,NULL,'远安县',NULL,172),(1556,NULL,'兴山县',NULL,172),(1557,NULL,'秭归县',NULL,172),(1558,NULL,'长阳土家族自治县',NULL,172),(1559,NULL,'五峰土家族自治县',NULL,172),(1560,NULL,'宜都市',NULL,172),(1561,NULL,'当阳市',NULL,172),(1562,NULL,'枝江市',NULL,172),(1563,NULL,'襄城区',NULL,173),(1564,NULL,'樊城区',NULL,173),(1565,NULL,'襄州区',NULL,173),(1566,NULL,'南漳县',NULL,173),(1567,NULL,'谷城县',NULL,173),(1568,NULL,'保康县',NULL,173),(1569,NULL,'老河口市',NULL,173),(1570,NULL,'枣阳市',NULL,173),(1571,NULL,'宜城市',NULL,173),(1572,NULL,'梁子湖区',NULL,174),(1573,NULL,'华容区',NULL,174),(1574,NULL,'鄂城区',NULL,174),(1575,NULL,'东宝区',NULL,175),(1576,NULL,'掇刀区',NULL,175),(1578,NULL,'沙洋县',NULL,175),(1579,NULL,'钟祥市',NULL,175),(1580,NULL,'孝南区',NULL,176),(1581,NULL,'孝昌县',NULL,176),(1582,NULL,'大悟县',NULL,176),(1583,NULL,'云梦县',NULL,176),(1584,NULL,'应城市',NULL,176),(1585,NULL,'安陆市',NULL,176),(1586,NULL,'汉川市',NULL,176),(1587,NULL,'沙市区',NULL,177),(1588,NULL,'荆州区',NULL,177),(1589,NULL,'公安县',NULL,177),(1590,NULL,'监利县',NULL,177),(1591,NULL,'江陵县',NULL,177),(1592,NULL,'石首市',NULL,177),(1593,NULL,'洪湖市',NULL,177),(1594,NULL,'松滋市',NULL,177),(1595,NULL,'黄州区',NULL,178),(1596,NULL,'团风县',NULL,178),(1597,NULL,'红安县',NULL,178),(1598,NULL,'罗田县',NULL,178),(1599,NULL,'英山县',NULL,178),(1600,NULL,'浠水县',NULL,178),(1601,NULL,'蕲春县',NULL,178),(1602,NULL,'黄梅县',NULL,178),(1603,NULL,'麻城市',NULL,178),(1604,NULL,'武穴市',NULL,178),(1605,NULL,'咸安区',NULL,179),(1606,NULL,'嘉鱼县',NULL,179),(1607,NULL,'通城县',NULL,179),(1608,NULL,'崇阳县',NULL,179),(1609,NULL,'通山县',NULL,179),(1610,NULL,'赤壁市',NULL,179),(1611,NULL,'曾都区',NULL,180),(1612,NULL,'广水市',NULL,180),(1613,NULL,'恩施市',NULL,181),(1614,NULL,'利川市',NULL,181),(1615,NULL,'建始县',NULL,181),(1616,NULL,'巴东县',NULL,181),(1617,NULL,'宣恩县',NULL,181),(1618,NULL,'咸丰县',NULL,181),(1619,NULL,'来凤县',NULL,181),(1620,NULL,'鹤峰县',NULL,181),(1621,NULL,'仙桃市',NULL,182),(1622,NULL,'潜江市',NULL,182),(1623,NULL,'天门市',NULL,182),(1624,NULL,'神农架林区',NULL,182),(1625,NULL,'芙蓉区',NULL,183),(1626,NULL,'天心区',NULL,183),(1627,NULL,'岳麓区',NULL,183),(1628,NULL,'开福区',NULL,183),(1629,NULL,'雨花区',NULL,183),(1630,NULL,'长沙县',NULL,183),(1631,NULL,'望城区',NULL,183),(1632,NULL,'宁乡县',NULL,183),(1633,NULL,'浏阳市',NULL,183),(1634,NULL,'荷塘区',NULL,184),(1635,NULL,'芦淞区',NULL,184),(1636,NULL,'石峰区',NULL,184),(1637,NULL,'天元区',NULL,184),(1638,NULL,'株洲县',NULL,184),(1639,NULL,'攸县',NULL,184),(1640,NULL,'茶陵县',NULL,184),(1641,NULL,'炎陵县',NULL,184),(1642,NULL,'醴陵市',NULL,184),(1643,NULL,'雨湖区',NULL,185),(1644,NULL,'岳塘区',NULL,185),(1645,NULL,'湘潭县',NULL,185),(1646,NULL,'湘乡市',NULL,185),(1647,NULL,'韶山市',NULL,185),(1648,NULL,'珠晖区',NULL,186),(1649,NULL,'雁峰区',NULL,186),(1650,NULL,'石鼓区',NULL,186),(1651,NULL,'蒸湘区',NULL,186),(1652,NULL,'南岳区',NULL,186),(1653,NULL,'衡阳县',NULL,186),(1654,NULL,'衡南县',NULL,186),(1655,NULL,'衡山县',NULL,186),(1656,NULL,'衡东县',NULL,186),(1657,NULL,'祁东县',NULL,186),(1658,NULL,'耒阳市',NULL,186),(1659,NULL,'常宁市',NULL,186),(1660,NULL,'双清区',NULL,187),(1661,NULL,'大祥区',NULL,187),(1662,NULL,'北塔区',NULL,187),(1663,NULL,'邵东县',NULL,187),(1664,NULL,'新邵县',NULL,187),(1665,NULL,'邵阳县',NULL,187),(1666,NULL,'隆回县',NULL,187),(1667,NULL,'洞口县',NULL,187),(1668,NULL,'绥宁县',NULL,187),(1669,NULL,'新宁县',NULL,187),(1670,NULL,'城步苗族自治县',NULL,187),(1671,NULL,'武冈市',NULL,187),(1672,NULL,'岳阳楼区',NULL,188),(1673,NULL,'云溪区',NULL,188),(1674,NULL,'君山区',NULL,188),(1675,NULL,'岳阳县',NULL,188),(1676,NULL,'华容县',NULL,188),(1678,NULL,'平江县',NULL,188),(1679,NULL,'汨罗市',NULL,188),(1680,NULL,'临湘市',NULL,188),(1681,NULL,'武陵区',NULL,189),(1682,NULL,'鼎城区',NULL,189),(1683,NULL,'安乡县',NULL,189),(1684,NULL,'汉寿县',NULL,189),(1685,NULL,'澧县',NULL,189),(1686,NULL,'临澧县',NULL,189),(1687,NULL,'桃源县',NULL,189),(1688,NULL,'石门县',NULL,189),(1689,NULL,'津市市',NULL,189),(1690,NULL,'永定区',NULL,190),(1691,NULL,'武陵源区',NULL,190),(1692,NULL,'慈利县',NULL,190),(1693,NULL,'桑植县',NULL,190),(1694,NULL,'资阳区',NULL,191),(1695,NULL,'赫山区',NULL,191),(1696,NULL,'南县',NULL,191),(1697,NULL,'桃江县',NULL,191),(1698,NULL,'安化县',NULL,191),(1699,NULL,'沅江市',NULL,191),(1700,NULL,'北湖区',NULL,192),(1701,NULL,'苏仙区',NULL,192),(1702,NULL,'桂阳县',NULL,192),(1703,NULL,'宜章县',NULL,192),(1704,NULL,'永兴县',NULL,192),(1705,NULL,'嘉禾县',NULL,192),(1706,NULL,'临武县',NULL,192),(1707,NULL,'汝城县',NULL,192),(1708,NULL,'桂东县',NULL,192),(1709,NULL,'安仁县',NULL,192),(1710,NULL,'资兴市',NULL,192),(1711,NULL,'零陵区',NULL,193),(1712,NULL,'冷水滩区',NULL,193),(1713,NULL,'祁阳县',NULL,193),(1714,NULL,'东安县',NULL,193),(1715,NULL,'双牌县',NULL,193),(1716,NULL,'道县',NULL,193),(1717,NULL,'江永县',NULL,193),(1718,NULL,'宁远县',NULL,193),(1719,NULL,'蓝山县',NULL,193),(1720,NULL,'新田县',NULL,193),(1721,NULL,'江华瑶族自治县',NULL,193),(1722,NULL,'鹤城区',NULL,194),(1723,NULL,'中方县',NULL,194),(1724,NULL,'沅陵县',NULL,194),(1725,NULL,'辰溪县',NULL,194),(1726,NULL,'溆浦县',NULL,194),(1727,NULL,'会同县',NULL,194),(1728,NULL,'麻阳苗族自治县',NULL,194),(1729,NULL,'新晃侗族自治县',NULL,194),(1730,NULL,'芷江侗族自治县',NULL,194),(1731,NULL,'靖州苗族侗族自治县',NULL,194),(1732,NULL,'通道侗族自治县',NULL,194),(1733,NULL,'洪江市',NULL,194),(1734,NULL,'娄星区',NULL,195),(1735,NULL,'双峰县',NULL,195),(1736,NULL,'新化县',NULL,195),(1737,NULL,'冷水江市',NULL,195),(1738,NULL,'涟源市',NULL,195),(1739,NULL,'吉首市',NULL,196),(1740,NULL,'泸溪县',NULL,196),(1741,NULL,'凤凰县',NULL,196),(1742,NULL,'花垣县',NULL,196),(1743,NULL,'保靖县',NULL,196),(1744,NULL,'古丈县',NULL,196),(1745,NULL,'永顺县',NULL,196),(1746,NULL,'龙山县',NULL,196),(1748,NULL,'荔湾区',NULL,197),(1749,NULL,'越秀区',NULL,197),(1750,NULL,'海珠区',NULL,197),(1751,NULL,'天河区',NULL,197),(1753,NULL,'白云区',NULL,197),(1754,NULL,'黄埔区',NULL,197),(1755,NULL,'番禺区',NULL,197),(1756,NULL,'花都区',NULL,197),(1757,NULL,'南沙区',NULL,197),(1758,NULL,'萝岗区',NULL,197),(1759,NULL,'武江区',NULL,198),(1760,NULL,'浈江区',NULL,198),(1761,NULL,'曲江区',NULL,198),(1762,NULL,'始兴县',NULL,198),(1763,NULL,'仁化县',NULL,198),(1764,NULL,'翁源县',NULL,198),(1765,NULL,'乳源瑶族自治县',NULL,198),(1766,NULL,'新丰县',NULL,198),(1767,NULL,'乐昌市',NULL,198),(1768,NULL,'南雄市',NULL,198),(1769,NULL,'罗湖区',NULL,199),(1770,NULL,'福田区',NULL,199),(1771,NULL,'南山区',NULL,199),(1772,NULL,'宝安区',NULL,199),(1773,NULL,'龙岗区',NULL,199),(1774,NULL,'盐田区',NULL,199),(1775,NULL,'香洲区',NULL,200),(1776,NULL,'斗门区',NULL,200),(1777,NULL,'金湾区',NULL,200),(1778,NULL,'龙湖区',NULL,201),(1780,NULL,'濠江区',NULL,201),(1781,NULL,'潮阳区',NULL,201),(1782,NULL,'潮南区',NULL,201),(1783,NULL,'澄海区',NULL,201),(1784,NULL,'南澳县',NULL,201),(1785,NULL,'禅城区',NULL,202),(1786,NULL,'南海区',NULL,202),(1787,NULL,'顺德区',NULL,202),(1788,NULL,'三水区',NULL,202),(1789,NULL,'高明区',NULL,202),(1790,NULL,'蓬江区',NULL,203),(1791,NULL,'江海区',NULL,203),(1792,NULL,'新会区',NULL,203),(1793,NULL,'台山市',NULL,203),(1794,NULL,'开平市',NULL,203),(1795,NULL,'鹤山市',NULL,203),(1796,NULL,'恩平市',NULL,203),(1797,NULL,'赤坎区',NULL,204),(1798,NULL,'霞山区',NULL,204),(1799,NULL,'坡头区',NULL,204),(1800,NULL,'麻章区',NULL,204),(1801,NULL,'遂溪县',NULL,204),(1802,NULL,'徐闻县',NULL,204),(1803,NULL,'廉江市',NULL,204),(1804,NULL,'雷州市',NULL,204),(1805,NULL,'吴川市',NULL,204),(1806,NULL,'茂南区',NULL,205),(1807,NULL,'茂港区',NULL,205),(1808,NULL,'电白县',NULL,205),(1809,NULL,'高州市',NULL,205),(1810,NULL,'化州市',NULL,205),(1811,NULL,'信宜市',NULL,205),(1812,NULL,'端州区',NULL,206),(1813,NULL,'鼎湖区',NULL,206),(1814,NULL,'广宁县',NULL,206),(1815,NULL,'怀集县',NULL,206),(1816,NULL,'封开县',NULL,206),(1817,NULL,'德庆县',NULL,206),(1818,NULL,'高要市',NULL,206),(1819,NULL,'四会市',NULL,206),(1820,NULL,'惠城区',NULL,207),(1821,NULL,'惠阳区',NULL,207),(1822,NULL,'博罗县',NULL,207),(1823,NULL,'惠东县',NULL,207),(1824,NULL,'龙门县',NULL,207),(1825,NULL,'梅江区',NULL,208),(1826,NULL,'梅县',NULL,208),(1827,NULL,'大埔县',NULL,208),(1828,NULL,'丰顺县',NULL,208),(1829,NULL,'五华县',NULL,208),(1830,NULL,'平远县',NULL,208),(1831,NULL,'蕉岭县',NULL,208),(1832,NULL,'兴宁市',NULL,208),(1833,NULL,'城区',NULL,209),(1834,NULL,'海丰县',NULL,209),(1835,NULL,'陆河县',NULL,209),(1836,NULL,'陆丰市',NULL,209),(1837,NULL,'源城区',NULL,210),(1838,NULL,'紫金县',NULL,210),(1839,NULL,'龙川县',NULL,210),(1840,NULL,'连平县',NULL,210),(1841,NULL,'和平县',NULL,210),(1842,NULL,'东源县',NULL,210),(1843,NULL,'江城区',NULL,211),(1844,NULL,'阳西县',NULL,211),(1845,NULL,'阳东县',NULL,211),(1846,NULL,'阳春市',NULL,211),(1847,NULL,'清城区',NULL,212),(1848,NULL,'佛冈县',NULL,212),(1849,NULL,'阳山县',NULL,212),(1850,NULL,'连山壮族瑶族自治县',NULL,212),(1851,NULL,'连南瑶族自治县',NULL,212),(1852,NULL,'清新县',NULL,212),(1853,NULL,'英德市',NULL,212),(1854,NULL,'连州市',NULL,212),(1855,NULL,'湘桥区',NULL,215),(1856,NULL,'潮安县',NULL,215),(1857,NULL,'饶平县',NULL,215),(1858,NULL,'榕城区',NULL,216),(1859,NULL,'揭东县',NULL,216),(1860,NULL,'揭西县',NULL,216),(1861,NULL,'惠来县',NULL,216),(1862,NULL,'普宁市',NULL,216),(1863,NULL,'云城区',NULL,217),(1864,NULL,'新兴县',NULL,217),(1865,NULL,'郁南县',NULL,217),(1866,NULL,'云安县',NULL,217),(1867,NULL,'罗定市',NULL,217),(1868,NULL,'兴宁区',NULL,218),(1869,NULL,'青秀区',NULL,218),(1870,NULL,'江南区',NULL,218),(1871,NULL,'西乡塘区',NULL,218),(1872,NULL,'良庆区',NULL,218),(1873,NULL,'邕宁区',NULL,218),(1874,NULL,'武鸣县',NULL,218),(1875,NULL,'隆安县',NULL,218),(1876,NULL,'马山县',NULL,218),(1877,NULL,'上林县',NULL,218),(1878,NULL,'宾阳县',NULL,218),(1880,NULL,'城中区',NULL,219),(1881,NULL,'鱼峰区',NULL,219),(1882,NULL,'柳南区',NULL,219),(1883,NULL,'柳北区',NULL,219),(1884,NULL,'柳江县',NULL,219),(1885,NULL,'柳城县',NULL,219),(1886,NULL,'鹿寨县',NULL,219),(1887,NULL,'融安县',NULL,219),(1888,NULL,'融水苗族自治县',NULL,219),(1889,NULL,'三江侗族自治县',NULL,219),(1890,NULL,'秀峰区',NULL,220),(1891,NULL,'叠彩区',NULL,220),(1892,NULL,'象山区',NULL,220),(1893,NULL,'七星区',NULL,220),(1894,NULL,'雁山区',NULL,220),(1895,NULL,'阳朔县',NULL,220),(1896,NULL,'临桂县',NULL,220),(1897,NULL,'灵川县',NULL,220),(1898,NULL,'全州县',NULL,220),(1899,NULL,'兴安县',NULL,220),(1900,NULL,'永福县',NULL,220),(1901,NULL,'灌阳县',NULL,220),(1902,NULL,'龙胜各族自治县',NULL,220),(1903,NULL,'资源县',NULL,220),(1904,NULL,'平乐县',NULL,220),(1905,NULL,'荔蒲县',NULL,220),(1906,NULL,'恭城瑶族自治县',NULL,220),(1907,NULL,'万秀区',NULL,221),(1908,NULL,'蝶山区',NULL,221),(1909,NULL,'长洲区',NULL,221),(1910,NULL,'苍梧县',NULL,221),(1911,NULL,'藤县',NULL,221),(1912,NULL,'蒙山县',NULL,221),(1913,NULL,'岑溪市',NULL,221),(1914,NULL,'海城区',NULL,222),(1915,NULL,'银海区',NULL,222),(1916,NULL,'铁山港区',NULL,222),(1917,NULL,'合浦县',NULL,222),(1918,NULL,'港口区',NULL,223),(1919,NULL,'防城区',NULL,223),(1920,NULL,'上思县',NULL,223),(1921,NULL,'东兴市',NULL,223),(1922,NULL,'钦南区',NULL,224),(1923,NULL,'钦北区',NULL,224),(1924,NULL,'灵山县',NULL,224),(1925,NULL,'浦北县',NULL,224),(1926,NULL,'港北区',NULL,225),(1927,NULL,'港南区',NULL,225),(1928,NULL,'覃塘区',NULL,225),(1929,NULL,'平南县',NULL,225),(1930,NULL,'桂平市',NULL,225),(1931,NULL,'玉州区',NULL,226),(1932,NULL,'容县',NULL,226),(1933,NULL,'陆川县',NULL,226),(1934,NULL,'博白县',NULL,226),(1935,NULL,'兴业县',NULL,226),(1936,NULL,'北流市',NULL,226),(1937,NULL,'右江区',NULL,227),(1938,NULL,'田阳县',NULL,227),(1939,NULL,'田东县',NULL,227),(1940,NULL,'平果县',NULL,227),(1941,NULL,'德保县',NULL,227),(1942,NULL,'靖西县',NULL,227),(1943,NULL,'那坡县',NULL,227),(1944,NULL,'凌云县',NULL,227),(1945,NULL,'乐业县',NULL,227),(1946,NULL,'田林县',NULL,227),(1947,NULL,'西林县',NULL,227),(1948,NULL,'隆林各族自治县',NULL,227),(1949,NULL,'八步区',NULL,228),(1950,NULL,'昭平县',NULL,228),(1951,NULL,'钟山县',NULL,228),(1952,NULL,'富川瑶族自治县',NULL,228),(1953,NULL,'金城江区',NULL,229),(1954,NULL,'南丹县',NULL,229),(1955,NULL,'天峨县',NULL,229),(1956,NULL,'凤山县',NULL,229),(1957,NULL,'东兰县',NULL,229),(1958,NULL,'罗城仫佬族自治县',NULL,229),(1959,NULL,'环江毛南族自治县',NULL,229),(1960,NULL,'巴马瑶族自治县',NULL,229),(1961,NULL,'都安瑶族自治县',NULL,229),(1962,NULL,'大化瑶族自治县',NULL,229),(1963,NULL,'宜州市',NULL,229),(1964,NULL,'兴宾区',NULL,230),(1965,NULL,'忻城县',NULL,230),(1966,NULL,'象州县',NULL,230),(1967,NULL,'武宣县',NULL,230),(1968,NULL,'金秀瑶族自治县',NULL,230),(1969,NULL,'合山市',NULL,230),(1970,NULL,'江洲区',NULL,231),(1971,NULL,'扶绥县',NULL,231),(1972,NULL,'宁明县',NULL,231),(1973,NULL,'龙州县',NULL,231),(1974,NULL,'大新县',NULL,231),(1975,NULL,'天等县',NULL,231),(1976,NULL,'凭祥市',NULL,231),(1977,NULL,'秀英区',NULL,232),(1978,NULL,'龙华区',NULL,232),(1980,NULL,'美兰区',NULL,232),(1981,NULL,'五指山市',NULL,233),(1982,NULL,'琼海市',NULL,233),(1983,NULL,'儋州市',NULL,233),(1984,NULL,'文昌市',NULL,233),(1985,NULL,'万宁市',NULL,233),(1986,NULL,'东方市',NULL,233),(1987,NULL,'定安县',NULL,233),(1988,NULL,'屯昌县',NULL,233),(1989,NULL,'澄迈县',NULL,233),(1990,NULL,'临高县',NULL,233),(1991,NULL,'白沙黎族自治县',NULL,233),(1992,NULL,'昌江黎族自治县',NULL,233),(1993,NULL,'乐东黎族自治县',NULL,233),(1994,NULL,'陵水黎族自治县',NULL,233),(1995,NULL,'保亭黎族苗族自治县',NULL,233),(1996,NULL,'琼中黎族苗族自治县',NULL,233),(1997,NULL,'西沙群岛',NULL,233),(1998,NULL,'南沙群岛',NULL,233),(1999,NULL,'中沙群岛的岛礁及其海域',NULL,233),(2000,NULL,'万州区',NULL,234),(2001,NULL,'涪陵区',NULL,234),(2002,NULL,'渝中区',NULL,234),(2003,NULL,'大渡口区',NULL,234),(2004,NULL,'江北区',NULL,234),(2005,NULL,'沙坪坝区',NULL,234),(2006,NULL,'九龙坡区',NULL,234),(2007,NULL,'南岸区',NULL,234),(2008,NULL,'北碚区',NULL,234),(2009,NULL,'綦江区',NULL,234),(2010,NULL,'大足区',NULL,234),(2011,NULL,'渝北区',NULL,234),(2012,NULL,'巴南区',NULL,234),(2013,NULL,'黔江区',NULL,234),(2014,NULL,'长寿区',NULL,234),(2015,NULL,'江津区',NULL,234),(2016,NULL,'潼南县',NULL,234),(2017,NULL,'铜梁县',NULL,234),(2019,NULL,'荣昌县',NULL,234),(2020,NULL,'璧山县',NULL,234),(2021,NULL,'梁平县',NULL,234),(2022,NULL,'城口县',NULL,234),(2023,NULL,'丰都县',NULL,234),(2024,NULL,'垫江县',NULL,234),(2025,NULL,'武隆县',NULL,234),(2026,NULL,'忠县',NULL,234),(2027,NULL,'开县',NULL,234),(2028,NULL,'云阳县',NULL,234),(2029,NULL,'奉节县',NULL,234),(2030,NULL,'巫山县',NULL,234),(2031,NULL,'巫溪县',NULL,234),(2032,NULL,'石柱土家族自治县',NULL,234),(2033,NULL,'秀山土家族苗族自治县',NULL,234),(2034,NULL,'酉阳土家族苗族自治县',NULL,234),(2035,NULL,'彭水苗族土家族自治县',NULL,234),(2040,NULL,'锦江区',NULL,235),(2041,NULL,'青羊区',NULL,235),(2042,NULL,'金牛区',NULL,235),(2043,NULL,'武侯区',NULL,235),(2044,NULL,'成华区',NULL,235),(2045,NULL,'龙泉驿区',NULL,235),(2046,NULL,'青白江区',NULL,235),(2047,NULL,'新都区',NULL,235),(2048,NULL,'温江区',NULL,235),(2049,NULL,'金堂县',NULL,235),(2050,NULL,'双流县',NULL,235),(2051,NULL,'郫县',NULL,235),(2052,NULL,'大邑县',NULL,235),(2053,NULL,'蒲江县',NULL,235),(2054,NULL,'新津县',NULL,235),(2055,NULL,'都江堰市',NULL,235),(2056,NULL,'彭州市',NULL,235),(2057,NULL,'邛崃市',NULL,235),(2058,NULL,'崇州市',NULL,235),(2059,NULL,'自流井区',NULL,236),(2060,NULL,'贡井区',NULL,236),(2061,NULL,'大安区',NULL,236),(2062,NULL,'沿滩区',NULL,236),(2063,NULL,'荣县',NULL,236),(2064,NULL,'富顺县',NULL,236),(2065,NULL,'东区',NULL,237),(2066,NULL,'西区',NULL,237),(2067,NULL,'仁和区',NULL,237),(2068,NULL,'米易县',NULL,237),(2069,NULL,'盐边县',NULL,237),(2070,NULL,'江阳区',NULL,238),(2071,NULL,'纳溪区',NULL,238),(2072,NULL,'龙马潭区',NULL,238),(2073,NULL,'泸县',NULL,238),(2074,NULL,'合江县',NULL,238),(2075,NULL,'叙永县',NULL,238),(2076,NULL,'古蔺县',NULL,238),(2077,NULL,'旌阳区',NULL,239),(2078,NULL,'中江县',NULL,239),(2079,NULL,'罗江县',NULL,239),(2080,NULL,'广汉市',NULL,239),(2081,NULL,'什邡市',NULL,239),(2082,NULL,'绵竹市',NULL,239),(2083,NULL,'涪城区',NULL,240),(2085,NULL,'三台县',NULL,240),(2086,NULL,'盐亭县',NULL,240),(2087,NULL,'安县',NULL,240),(2088,NULL,'梓潼县',NULL,240),(2089,NULL,'北川羌族自治县',NULL,240),(2090,NULL,'平武县',NULL,240),(2091,NULL,'江油市',NULL,240),(2092,NULL,'利州区',NULL,241),(2093,NULL,'元坝区',NULL,241),(2094,NULL,'朝天区',NULL,241),(2095,NULL,'旺苍县',NULL,241),(2096,NULL,'青川县',NULL,241),(2097,NULL,'剑阁县',NULL,241),(2098,NULL,'苍溪县',NULL,241),(2099,NULL,'船山区',NULL,242),(2100,NULL,'安居区',NULL,242),(2101,NULL,'蓬溪县',NULL,242),(2102,NULL,'射洪县',NULL,242),(2103,NULL,'大英县',NULL,242),(2104,NULL,'市中区',NULL,243),(2105,NULL,'东兴区',NULL,243),(2106,NULL,'威远县',NULL,243),(2107,NULL,'资中县',NULL,243),(2108,NULL,'隆昌县',NULL,243),(2109,NULL,'市中区',NULL,244),(2110,NULL,'沙湾区',NULL,244),(2111,NULL,'五通桥区',NULL,244),(2112,NULL,'金口河区',NULL,244),(2113,NULL,'犍为县',NULL,244),(2114,NULL,'井研县',NULL,244),(2115,NULL,'夹江县',NULL,244),(2116,NULL,'沐川县',NULL,244),(2117,NULL,'峨边彝族自治县',NULL,244),(2118,NULL,'马边彝族自治县',NULL,244),(2119,NULL,'峨眉山市',NULL,244),(2120,NULL,'顺庆区',NULL,245),(2121,NULL,'高坪区',NULL,245),(2122,NULL,'嘉陵区',NULL,245),(2123,NULL,'南部县',NULL,245),(2124,NULL,'营山县',NULL,245),(2125,NULL,'蓬安县',NULL,245),(2126,NULL,'仪陇县',NULL,245),(2127,NULL,'西充县',NULL,245),(2128,NULL,'阆中市',NULL,245),(2129,NULL,'东坡区',NULL,246),(2130,NULL,'仁寿县',NULL,246),(2131,NULL,'彭山县',NULL,246),(2132,NULL,'洪雅县',NULL,246),(2133,NULL,'丹棱县',NULL,246),(2134,NULL,'青神县',NULL,246),(2135,NULL,'翠屏区',NULL,247),(2136,NULL,'宜宾县',NULL,247),(2137,NULL,'南溪区',NULL,247),(2138,NULL,'江安县',NULL,247),(2139,NULL,'长宁县',NULL,247),(2140,NULL,'高县',NULL,247),(2141,NULL,'珙县',NULL,247),(2142,NULL,'筠连县',NULL,247),(2143,NULL,'兴文县',NULL,247),(2144,NULL,'屏山县',NULL,247),(2145,NULL,'广安区',NULL,248),(2146,NULL,'岳池县',NULL,248),(2147,NULL,'武胜县',NULL,248),(2148,NULL,'邻水县',NULL,248),(2149,NULL,'华蓥市',NULL,248),(2150,NULL,'通川区',NULL,249),(2151,NULL,'达县',NULL,249),(2152,NULL,'宣汉县',NULL,249),(2153,NULL,'开江县',NULL,249),(2154,NULL,'大竹县',NULL,249),(2155,NULL,'渠县',NULL,249),(2156,NULL,'万源市',NULL,249),(2157,NULL,'雨城区',NULL,250),(2158,NULL,'名山县',NULL,250),(2159,NULL,'荥经县',NULL,250),(2160,NULL,'汉源县',NULL,250),(2161,NULL,'石棉县',NULL,250),(2162,NULL,'天全县',NULL,250),(2163,NULL,'芦山县',NULL,250),(2164,NULL,'宝兴县',NULL,250),(2165,NULL,'巴州区',NULL,251),(2166,NULL,'通江县',NULL,251),(2167,NULL,'南江县',NULL,251),(2168,NULL,'平昌县',NULL,251),(2169,NULL,'雁江区',NULL,252),(2170,NULL,'安岳县',NULL,252),(2171,NULL,'乐至县',NULL,252),(2172,NULL,'简阳市',NULL,252),(2173,NULL,'汶川县',NULL,253),(2174,NULL,'理县',NULL,253),(2175,NULL,'茂县',NULL,253),(2176,NULL,'松潘县',NULL,253),(2177,NULL,'九寨沟县',NULL,253),(2178,NULL,'金川县',NULL,253),(2179,NULL,'小金县',NULL,253),(2180,NULL,'黑水县',NULL,253),(2181,NULL,'马尔康县',NULL,253),(2182,NULL,'壤塘县',NULL,253),(2183,NULL,'阿坝县',NULL,253),(2185,NULL,'红原县',NULL,253),(2186,NULL,'康定县',NULL,254),(2187,NULL,'泸定县',NULL,254),(2188,NULL,'丹巴县',NULL,254),(2189,NULL,'九龙县',NULL,254),(2190,NULL,'雅江县',NULL,254),(2191,NULL,'道孚县',NULL,254),(2192,NULL,'炉霍县',NULL,254),(2193,NULL,'甘孜县',NULL,254),(2194,NULL,'新龙县',NULL,254),(2195,NULL,'德格县',NULL,254),(2196,NULL,'白玉县',NULL,254),(2197,NULL,'石渠县',NULL,254),(2198,NULL,'色达县',NULL,254),(2199,NULL,'理塘县',NULL,254),(2200,NULL,'巴塘县',NULL,254),(2201,NULL,'乡城县',NULL,254),(2202,NULL,'稻城县',NULL,254),(2203,NULL,'得荣县',NULL,254),(2204,NULL,'西昌市',NULL,255),(2205,NULL,'木里藏族自治县',NULL,255),(2206,NULL,'盐源县',NULL,255),(2207,NULL,'德昌县',NULL,255),(2208,NULL,'会理县',NULL,255),(2209,NULL,'会东县',NULL,255),(2210,NULL,'宁南县',NULL,255),(2211,NULL,'普格县',NULL,255),(2212,NULL,'布拖县',NULL,255),(2213,NULL,'金阳县',NULL,255),(2214,NULL,'昭觉县',NULL,255),(2215,NULL,'喜德县',NULL,255),(2216,NULL,'冕宁县',NULL,255),(2217,NULL,'越西县',NULL,255),(2218,NULL,'甘洛县',NULL,255),(2219,NULL,'美姑县',NULL,255),(2220,NULL,'雷波县',NULL,255),(2221,NULL,'南明区',NULL,256),(2222,NULL,'云岩区',NULL,256),(2223,NULL,'花溪区',NULL,256),(2224,NULL,'乌当区',NULL,256),(2225,NULL,'白云区',NULL,256),(2226,NULL,'小河区',NULL,256),(2227,NULL,'开阳县',NULL,256),(2228,NULL,'息烽县',NULL,256),(2229,NULL,'修文县',NULL,256),(2230,NULL,'清镇市',NULL,256),(2231,NULL,'钟山区',NULL,257),(2232,NULL,'六枝特区',NULL,257),(2233,NULL,'水城县',NULL,257),(2234,NULL,'盘县',NULL,257),(2235,NULL,'红花岗区',NULL,258),(2236,NULL,'汇川区',NULL,258),(2237,NULL,'遵义县',NULL,258),(2238,NULL,'桐梓县',NULL,258),(2239,NULL,'绥阳县',NULL,258),(2240,NULL,'正安县',NULL,258),(2241,NULL,'道真仡佬族苗族自治县',NULL,258),(2242,NULL,'务川仡佬族苗族自治县',NULL,258),(2243,NULL,'凤冈县',NULL,258),(2244,NULL,'湄潭县',NULL,258),(2245,NULL,'余庆县',NULL,258),(2246,NULL,'习水县',NULL,258),(2247,NULL,'赤水市',NULL,258),(2248,NULL,'仁怀市',NULL,258),(2249,NULL,'西秀区',NULL,259),(2250,NULL,'平坝县',NULL,259),(2251,NULL,'普定县',NULL,259),(2252,NULL,'镇宁布依族苗族自治县',NULL,259),(2253,NULL,'关岭布依族苗族自治县',NULL,259),(2254,NULL,'紫云苗族布依族自治县',NULL,259),(2256,NULL,'碧江区',NULL,260),(2257,NULL,'万山区',NULL,260),(2258,NULL,'江口县',NULL,260),(2259,NULL,'玉屏侗族自治县',NULL,260),(2260,NULL,'石阡县',NULL,260),(2262,NULL,'思南县',NULL,260),(2263,NULL,'印江土家族苗族自治县',NULL,260),(2264,NULL,'德江县',NULL,260),(2265,NULL,'兴义市',NULL,261),(2266,NULL,'兴仁县',NULL,261),(2267,NULL,'普安县',NULL,261),(2268,NULL,'晴隆县',NULL,261),(2269,NULL,'贞丰县',NULL,261),(2270,NULL,'望谟县',NULL,261),(2271,NULL,'册亨县',NULL,261),(2272,NULL,'安龙县',NULL,261),(2274,NULL,'大方县',NULL,262),(2275,NULL,'黔西县',NULL,262),(2276,NULL,'金沙县',NULL,262),(2277,NULL,'织金县',NULL,262),(2278,NULL,'纳雍县',NULL,262),(2279,NULL,'威宁彝族回族苗族自治县',NULL,262),(2280,NULL,'赫章县',NULL,262),(2281,NULL,'凯里市',NULL,263),(2282,NULL,'黄平县',NULL,263),(2283,NULL,'施秉县',NULL,263),(2284,NULL,'三穗县',NULL,263),(2285,NULL,'镇远县',NULL,263),(2286,NULL,'岑巩县',NULL,263),(2288,NULL,'锦屏县',NULL,263),(2289,NULL,'剑河县',NULL,263),(2290,NULL,'台江县',NULL,263),(2291,NULL,'黎平县',NULL,263),(2292,NULL,'榕江县',NULL,263),(2293,NULL,'从江县',NULL,263),(2294,NULL,'雷山县',NULL,263),(2295,NULL,'麻江县',NULL,263),(2296,NULL,'丹寨县',NULL,263),(2297,NULL,'都匀市',NULL,264),(2298,NULL,'福泉市',NULL,264),(2299,NULL,'荔波县',NULL,264),(2300,NULL,'贵定县',NULL,264),(2301,NULL,'瓮安县',NULL,264),(2302,NULL,'独山县',NULL,264),(2303,NULL,'平塘县',NULL,264),(2304,NULL,'罗甸县',NULL,264),(2305,NULL,'长顺县',NULL,264),(2306,NULL,'龙里县',NULL,264),(2307,NULL,'惠水县',NULL,264),(2308,NULL,'三都水族自治县',NULL,264),(2309,NULL,'五华区',NULL,265),(2310,NULL,'盘龙区',NULL,265),(2311,NULL,'官渡区',NULL,265),(2312,NULL,'西山区',NULL,265),(2313,NULL,'东川区',NULL,265),(2314,NULL,'呈贡县',NULL,265),(2315,NULL,'晋宁县',NULL,265),(2316,NULL,'富民县',NULL,265),(2317,NULL,'宜良县',NULL,265),(2318,NULL,'石林彝族自治县',NULL,265),(2319,NULL,'嵩明县',NULL,265),(2320,NULL,'禄劝彝族苗族自治县',NULL,265),(2321,NULL,'寻甸回族彝族自治县',NULL,265),(2322,NULL,'安宁市',NULL,265),(2323,NULL,'麒麟区',NULL,266),(2324,NULL,'马龙县',NULL,266),(2325,NULL,'陆良县',NULL,266),(2326,NULL,'师宗县',NULL,266),(2327,NULL,'罗平县',NULL,266),(2328,NULL,'富源县',NULL,266),(2329,NULL,'会泽县',NULL,266),(2330,NULL,'沾益县',NULL,266),(2331,NULL,'宣威市',NULL,266),(2332,NULL,'红塔区',NULL,267),(2333,NULL,'江川县',NULL,267),(2334,NULL,'澄江县',NULL,267),(2335,NULL,'通海县',NULL,267),(2336,NULL,'华宁县',NULL,267),(2337,NULL,'易门县',NULL,267),(2338,NULL,'峨山彝族自治县',NULL,267),(2339,NULL,'新平彝族傣族自治县',NULL,267),(2340,NULL,'元江哈尼族彝族傣族自治县',NULL,267),(2341,NULL,'隆阳区',NULL,268),(2342,NULL,'施甸县',NULL,268),(2343,NULL,'腾冲县',NULL,268),(2344,NULL,'龙陵县',NULL,268),(2345,NULL,'昌宁县',NULL,268),(2346,NULL,'昭阳区',NULL,269),(2347,NULL,'鲁甸县',NULL,269),(2348,NULL,'巧家县',NULL,269),(2349,NULL,'盐津县',NULL,269),(2350,NULL,'大关县',NULL,269),(2351,NULL,'永善县',NULL,269),(2352,NULL,'绥江县',NULL,269),(2353,NULL,'镇雄县',NULL,269),(2354,NULL,'彝良县',NULL,269),(2355,NULL,'威信县',NULL,269),(2356,NULL,'水富县',NULL,269),(2357,NULL,'古城区',NULL,270),(2358,NULL,'玉龙纳西族自治县',NULL,270),(2359,NULL,'永胜县',NULL,270),(2360,NULL,'华坪县',NULL,270),(2361,NULL,'宁蒗彝族自治县',NULL,270),(2362,NULL,'思茅区',NULL,271),(2363,NULL,'宁洱哈尼族彝族自治县',NULL,271),(2364,NULL,'墨江哈尼族自治县',NULL,271),(2365,NULL,'景东彝族自治县',NULL,271),(2366,NULL,'景谷傣族彝族自治县',NULL,271),(2367,NULL,'镇沅彝族哈尼族拉祜族自治县',NULL,271),(2368,NULL,'江城哈尼族彝族自治县',NULL,271),(2369,NULL,'孟连傣族拉祜族佤族自治县',NULL,271),(2370,NULL,'澜沧拉祜族自治县',NULL,271),(2371,NULL,'西盟佤族自治县',NULL,271),(2372,NULL,'临翔区',NULL,272),(2373,NULL,'凤庆县',NULL,272),(2374,NULL,'云县',NULL,272),(2375,NULL,'永德县',NULL,272),(2376,NULL,'镇康县',NULL,272),(2377,NULL,'双江拉祜族佤族布朗族傣族自治县',NULL,272),(2378,NULL,'耿马傣族佤族自治县',NULL,272),(2379,NULL,'沧源佤族自治县',NULL,272),(2380,NULL,'楚雄市',NULL,273),(2381,NULL,'双柏县',NULL,273),(2382,NULL,'牟定县',NULL,273),(2383,NULL,'南华县',NULL,273),(2384,NULL,'姚安县',NULL,273),(2385,NULL,'大姚县',NULL,273),(2386,NULL,'永仁县',NULL,273),(2388,NULL,'武定县',NULL,273),(2389,NULL,'禄丰县',NULL,273),(2390,NULL,'个旧市',NULL,274),(2391,NULL,'开远市',NULL,274),(2392,NULL,'蒙自县',NULL,274),(2393,NULL,'屏边苗族自治县',NULL,274),(2394,NULL,'建水县',NULL,274),(2395,NULL,'石屏县',NULL,274),(2396,NULL,'弥勒县',NULL,274),(2397,NULL,'泸西县',NULL,274),(2398,NULL,'元阳县',NULL,274),(2399,NULL,'红河县',NULL,274),(2400,NULL,'金平苗族瑶族傣族自治县',NULL,274),(2401,NULL,'绿春县',NULL,274),(2402,NULL,'河口瑶族自治县',NULL,274),(2403,NULL,'文山市',NULL,275),(2404,NULL,'砚山县',NULL,275),(2405,NULL,'西畴县',NULL,275),(2406,NULL,'麻栗坡县',NULL,275),(2407,NULL,'马关县',NULL,275),(2408,NULL,'丘北县',NULL,275),(2409,NULL,'广南县',NULL,275),(2410,NULL,'富宁县',NULL,275),(2411,NULL,'景洪市',NULL,276),(2412,NULL,'勐海县',NULL,276),(2413,NULL,'勐腊县',NULL,276),(2414,NULL,'大理市',NULL,277),(2415,NULL,'漾濞彝族自治县',NULL,277),(2416,NULL,'祥云县',NULL,277),(2417,NULL,'宾川县',NULL,277),(2418,NULL,'弥渡县',NULL,277),(2419,NULL,'南涧彝族自治县',NULL,277),(2420,NULL,'巍山彝族回族自治县',NULL,277),(2421,NULL,'永平县',NULL,277),(2422,NULL,'云龙县',NULL,277),(2423,NULL,'洱源县',NULL,277),(2424,NULL,'剑川县',NULL,277),(2425,NULL,'鹤庆县',NULL,277),(2426,NULL,'瑞丽市',NULL,278),(2427,NULL,'芒市',NULL,278),(2428,NULL,'梁河县',NULL,278),(2429,NULL,'盈江县',NULL,278),(2430,NULL,'陇川县',NULL,278),(2431,NULL,'泸水县',NULL,279),(2432,NULL,'福贡县',NULL,279),(2433,NULL,'贡山独龙族怒族自治县',NULL,279),(2434,NULL,'兰坪白族普米族自治县',NULL,279),(2435,NULL,'香格里拉县',NULL,280),(2436,NULL,'德钦县',NULL,280),(2437,NULL,'维西傈僳族自治县',NULL,280),(2438,NULL,'城关区',NULL,281),(2439,NULL,'林周县',NULL,281),(2440,NULL,'当雄县',NULL,281),(2441,NULL,'尼木县',NULL,281),(2442,NULL,'曲水县',NULL,281),(2443,NULL,'堆龙德庆县',NULL,281),(2444,NULL,'达孜县',NULL,281),(2445,NULL,'墨竹工卡县',NULL,281),(2446,NULL,'昌都县',NULL,282),(2447,NULL,'江达县',NULL,282),(2448,NULL,'贡觉县',NULL,282),(2449,NULL,'类乌齐县',NULL,282),(2450,NULL,'丁青县',NULL,282),(2451,NULL,'察雅县',NULL,282),(2452,NULL,'八宿县',NULL,282),(2453,NULL,'左贡县',NULL,282),(2454,NULL,'芒康县',NULL,282),(2455,NULL,'洛隆县',NULL,282),(2456,NULL,'边坝县',NULL,282),(2457,NULL,'乃东县',NULL,283),(2458,NULL,'扎囊县',NULL,283),(2459,NULL,'贡嘎县',NULL,283),(2460,NULL,'桑日县',NULL,283),(2461,NULL,'琼结县',NULL,283),(2462,NULL,'曲松县',NULL,283),(2463,NULL,'措美县',NULL,283),(2464,NULL,'洛扎县',NULL,283),(2465,NULL,'加查县',NULL,283),(2466,NULL,'隆子县',NULL,283),(2467,NULL,'错那县',NULL,283),(2468,NULL,'浪卡子县',NULL,283),(2469,NULL,'日喀则市',NULL,284),(2470,NULL,'南木林县',NULL,284),(2471,NULL,'江孜县',NULL,284),(2472,NULL,'定日县',NULL,284),(2473,NULL,'萨迦县',NULL,284),(2474,NULL,'拉孜县',NULL,284),(2475,NULL,'昂仁县',NULL,284),(2476,NULL,'谢通门县',NULL,284),(2477,NULL,'白朗县',NULL,284),(2478,NULL,'仁布县',NULL,284),(2479,NULL,'康马县',NULL,284),(2480,NULL,'定结县',NULL,284),(2481,NULL,'仲巴县',NULL,284),(2482,NULL,'亚东县',NULL,284),(2483,NULL,'吉隆县',NULL,284),(2484,NULL,'聂拉木县',NULL,284),(2485,NULL,'萨嘎县',NULL,284),(2486,NULL,'岗巴县',NULL,284),(2488,NULL,'嘉黎县',NULL,285),(2489,NULL,'比如县',NULL,285),(2490,NULL,'聂荣县',NULL,285),(2491,NULL,'安多县',NULL,285),(2492,NULL,'申扎县',NULL,285),(2493,NULL,'索县',NULL,285),(2494,NULL,'班戈县',NULL,285),(2495,NULL,'巴青县',NULL,285),(2496,NULL,'尼玛县',NULL,285),(2497,NULL,'普兰县',NULL,286),(2498,NULL,'札达县',NULL,286),(2499,NULL,'噶尔县',NULL,286),(2500,NULL,'日土县',NULL,286),(2501,NULL,'革吉县',NULL,286),(2502,NULL,'改则县',NULL,286),(2503,NULL,'措勤县',NULL,286),(2504,NULL,'林芝县',NULL,287),(2505,NULL,'工布江达县',NULL,287),(2506,NULL,'米林县',NULL,287),(2507,NULL,'墨脱县',NULL,287),(2508,NULL,'波密县',NULL,287),(2509,NULL,'察隅县',NULL,287),(2510,NULL,'朗县',NULL,287),(2511,NULL,'新城区',NULL,288),(2512,NULL,'碑林区',NULL,288),(2513,NULL,'莲湖区',NULL,288),(2514,NULL,'灞桥区',NULL,288),(2515,NULL,'未央区',NULL,288),(2516,NULL,'雁塔区',NULL,288),(2517,NULL,'阎良区',NULL,288),(2518,NULL,'临潼区',NULL,288),(2519,NULL,'长安区',NULL,288),(2520,NULL,'蓝田县',NULL,288),(2521,NULL,'周至县',NULL,288),(2522,NULL,'户县',NULL,288),(2523,NULL,'高陵县',NULL,288),(2524,NULL,'王益区',NULL,289),(2525,NULL,'印台区',NULL,289),(2526,NULL,'耀州区',NULL,289),(2527,NULL,'宜君县',NULL,289),(2528,NULL,'渭滨区',NULL,290),(2529,NULL,'金台区',NULL,290),(2530,NULL,'陈仓区',NULL,290),(2531,NULL,'凤翔县',NULL,290),(2532,NULL,'岐山县',NULL,290),(2533,NULL,'扶风县',NULL,290),(2534,NULL,'眉县',NULL,290),(2535,NULL,'陇县',NULL,290),(2536,NULL,'千阳县',NULL,290),(2537,NULL,'麟游县',NULL,290),(2538,NULL,'凤县',NULL,290),(2539,NULL,'太白县',NULL,290),(2540,NULL,'秦都区',NULL,291),(2541,NULL,'杨凌区',NULL,291),(2542,NULL,'渭城区',NULL,291),(2543,NULL,'三原县',NULL,291),(2544,NULL,'泾阳县',NULL,291),(2545,NULL,'乾县',NULL,291),(2546,NULL,'礼泉县',NULL,291),(2547,NULL,'永寿县',NULL,291),(2548,NULL,'彬县',NULL,291),(2549,NULL,'长武县',NULL,291),(2550,NULL,'旬邑县',NULL,291),(2551,NULL,'淳化县',NULL,291),(2552,NULL,'武功县',NULL,291),(2553,NULL,'兴平市',NULL,291),(2554,NULL,'临渭区',NULL,292),(2555,NULL,'华县',NULL,292),(2556,NULL,'潼关县',NULL,292),(2557,NULL,'大荔县',NULL,292),(2558,NULL,'合阳县',NULL,292),(2559,NULL,'澄城县',NULL,292),(2560,NULL,'蒲城县',NULL,292),(2561,NULL,'白水县',NULL,292),(2562,NULL,'富平县',NULL,292),(2563,NULL,'韩城市',NULL,292),(2564,NULL,'华阴市',NULL,292),(2565,NULL,'宝塔区',NULL,293),(2566,NULL,'延长县',NULL,293),(2567,NULL,'延川县',NULL,293),(2568,NULL,'子长县',NULL,293),(2569,NULL,'安塞县',NULL,293),(2570,NULL,'志丹县',NULL,293),(2571,NULL,'吴起县',NULL,293),(2572,NULL,'甘泉县',NULL,293),(2573,NULL,'富县',NULL,293),(2574,NULL,'洛川县',NULL,293),(2575,NULL,'宜川县',NULL,293),(2576,NULL,'黄龙县',NULL,293),(2577,NULL,'黄陵县',NULL,293),(2578,NULL,'汉台区',NULL,294),(2579,NULL,'南郑县',NULL,294),(2580,NULL,'城固县',NULL,294),(2581,NULL,'洋县',NULL,294),(2582,NULL,'西乡县',NULL,294),(2583,NULL,'勉县',NULL,294),(2584,NULL,'宁强县',NULL,294),(2585,NULL,'略阳县',NULL,294),(2586,NULL,'镇巴县',NULL,294),(2588,NULL,'佛坪县',NULL,294),(2589,NULL,'榆阳区',NULL,295),(2590,NULL,'神木县',NULL,295),(2591,NULL,'府谷县',NULL,295),(2592,NULL,'横山县',NULL,295),(2593,NULL,'靖边县',NULL,295),(2594,NULL,'定边县',NULL,295),(2595,NULL,'绥德县',NULL,295),(2596,NULL,'米脂县',NULL,295),(2597,NULL,'佳县',NULL,295),(2598,NULL,'吴堡县',NULL,295),(2599,NULL,'清涧县',NULL,295),(2600,NULL,'子洲县',NULL,295),(2601,NULL,'汉滨区',NULL,296),(2602,NULL,'汉阴县',NULL,296),(2603,NULL,'石泉县',NULL,296),(2604,NULL,'宁陕县',NULL,296),(2605,NULL,'紫阳县',NULL,296),(2606,NULL,'岚皋县',NULL,296),(2607,NULL,'平利县',NULL,296),(2608,NULL,'镇坪县',NULL,296),(2609,NULL,'旬阳县',NULL,296),(2610,NULL,'白河县',NULL,296),(2611,NULL,'商州区',NULL,297),(2612,NULL,'洛南县',NULL,297),(2613,NULL,'丹凤县',NULL,297),(2614,NULL,'商南县',NULL,297),(2615,NULL,'山阳县',NULL,297),(2616,NULL,'镇安县',NULL,297),(2617,NULL,'柞水县',NULL,297),(2618,NULL,'城关区',NULL,298),(2619,NULL,'七里河区',NULL,298),(2620,NULL,'西固区',NULL,298),(2621,NULL,'安宁区',NULL,298),(2622,NULL,'红古区',NULL,298),(2623,NULL,'永登县',NULL,298),(2624,NULL,'皋兰县',NULL,298),(2625,NULL,'榆中县',NULL,298),(2626,NULL,'金川区',NULL,300),(2627,NULL,'永昌县',NULL,300),(2628,NULL,'白银区',NULL,301),(2629,NULL,'平川区',NULL,301),(2630,NULL,'靖远县',NULL,301),(2631,NULL,'会宁县',NULL,301),(2632,NULL,'景泰县',NULL,301),(2633,NULL,'秦州区',NULL,302),(2634,NULL,'麦积区',NULL,302),(2635,NULL,'清水县',NULL,302),(2636,NULL,'秦安县',NULL,302),(2637,NULL,'甘谷县',NULL,302),(2638,NULL,'武山县',NULL,302),(2639,NULL,'张家川回族自治县',NULL,302),(2640,NULL,'凉州区',NULL,303),(2641,NULL,'民勤县',NULL,303),(2642,NULL,'古浪县',NULL,303),(2643,NULL,'天祝藏族自治县',NULL,303),(2644,NULL,'甘州区',NULL,304),(2645,NULL,'肃南裕固族自治县',NULL,304),(2646,NULL,'民乐县',NULL,304),(2647,NULL,'临泽县',NULL,304),(2648,NULL,'高台县',NULL,304),(2649,NULL,'山丹县',NULL,304),(2650,NULL,'崆峒区',NULL,305),(2651,NULL,'泾川县',NULL,305),(2652,NULL,'灵台县',NULL,305),(2653,NULL,'崇信县',NULL,305),(2654,NULL,'华亭县',NULL,305),(2655,NULL,'庄浪县',NULL,305),(2656,NULL,'静宁县',NULL,305),(2657,NULL,'肃州区',NULL,306),(2658,NULL,'金塔县',NULL,306),(2659,NULL,'瓜州县',NULL,306),(2660,NULL,'肃北蒙古族自治县',NULL,306),(2661,NULL,'阿克塞哈萨克族自治县',NULL,306),(2662,NULL,'玉门市',NULL,306),(2663,NULL,'敦煌市',NULL,306),(2664,NULL,'西峰区',NULL,307),(2665,NULL,'庆城县',NULL,307),(2666,NULL,'环县',NULL,307),(2667,NULL,'华池县',NULL,307),(2668,NULL,'合水县',NULL,307),(2669,NULL,'正宁县',NULL,307),(2670,NULL,'宁县',NULL,307),(2671,NULL,'镇原县',NULL,307),(2672,NULL,'安定区',NULL,308),(2673,NULL,'通渭县',NULL,308),(2674,NULL,'陇西县',NULL,308),(2675,NULL,'渭源县',NULL,308),(2676,NULL,'临洮县',NULL,308),(2677,NULL,'漳县',NULL,308),(2678,NULL,'岷县',NULL,308),(2679,NULL,'武都区',NULL,309),(2680,NULL,'成县',NULL,309),(2681,NULL,'文县',NULL,309),(2682,NULL,'宕昌县',NULL,309),(2683,NULL,'康县',NULL,309),(2684,NULL,'西和县',NULL,309),(2685,NULL,'礼县',NULL,309),(2686,NULL,'徽县',NULL,309),(2688,NULL,'临夏市',NULL,310),(2689,NULL,'临夏县',NULL,310),(2690,NULL,'康乐县',NULL,310),(2691,NULL,'永靖县',NULL,310),(2692,NULL,'广河县',NULL,310),(2693,NULL,'和政县',NULL,310),(2694,NULL,'东乡族自治县',NULL,310),(2695,NULL,'积石山保安族东乡族撒拉族自治县',NULL,310),(2696,NULL,'合作市',NULL,311),(2697,NULL,'临潭县',NULL,311),(2698,NULL,'卓尼县',NULL,311),(2699,NULL,'舟曲县',NULL,311),(2700,NULL,'迭部县',NULL,311),(2701,NULL,'玛曲县',NULL,311),(2702,NULL,'碌曲县',NULL,311),(2703,NULL,'夏河县',NULL,311),(2704,NULL,'城东区',NULL,312),(2705,NULL,'城中区',NULL,312),(2706,NULL,'城西区',NULL,312),(2707,NULL,'城北区',NULL,312),(2708,NULL,'大通回族土族自治县',NULL,312),(2709,NULL,'湟中县',NULL,312),(2710,NULL,'湟源县',NULL,312),(2711,NULL,'平安县',NULL,313),(2712,NULL,'民和回族土族自治县',NULL,313),(2713,NULL,'乐都县',NULL,313),(2714,NULL,'互助土族自治县',NULL,313),(2715,NULL,'化隆回族自治县',NULL,313),(2716,NULL,'循化撒拉族自治县',NULL,313),(2717,NULL,'门源回族自治县',NULL,314),(2718,NULL,'祁连县',NULL,314),(2719,NULL,'海晏县',NULL,314),(2720,NULL,'刚察县',NULL,314),(2721,NULL,'同仁县',NULL,315),(2722,NULL,'尖扎县',NULL,315),(2723,NULL,'泽库县',NULL,315),(2724,NULL,'河南蒙古族自治县',NULL,315),(2725,NULL,'共和县',NULL,316),(2726,NULL,'同德县',NULL,316),(2727,NULL,'贵德县',NULL,316),(2728,NULL,'兴海县',NULL,316),(2729,NULL,'贵南县',NULL,316),(2730,NULL,'玛沁县',NULL,317),(2731,NULL,'班玛县',NULL,317),(2732,NULL,'甘德县',NULL,317),(2733,NULL,'达日县',NULL,317),(2734,NULL,'久治县',NULL,317),(2735,NULL,'玛多县',NULL,317),(2736,NULL,'玉树县',NULL,318),(2737,NULL,'杂多县',NULL,318),(2738,NULL,'称多县',NULL,318),(2739,NULL,'治多县',NULL,318),(2740,NULL,'囊谦县',NULL,318),(2741,NULL,'曲麻莱县',NULL,318),(2742,NULL,'格尔木市',NULL,319),(2743,NULL,'德令哈市',NULL,319),(2744,NULL,'乌兰县',NULL,319),(2745,NULL,'都兰县',NULL,319),(2746,NULL,'天峻县',NULL,319),(2747,NULL,'兴庆区',NULL,320),(2748,NULL,'西夏区',NULL,320),(2749,NULL,'金凤区',NULL,320),(2750,NULL,'永宁县',NULL,320),(2751,NULL,'贺兰县',NULL,320),(2752,NULL,'灵武市',NULL,320),(2753,NULL,'大武口区',NULL,321),(2754,NULL,'惠农区',NULL,321),(2755,NULL,'平罗县',NULL,321),(2756,NULL,'利通区',NULL,322),(2757,NULL,'盐池县',NULL,322),(2758,NULL,'同心县',NULL,322),(2759,NULL,'青铜峡市',NULL,322),(2760,NULL,'原州区',NULL,323),(2761,NULL,'西吉县',NULL,323),(2762,NULL,'隆德县',NULL,323),(2763,NULL,'泾源县',NULL,323),(2764,NULL,'彭阳县',NULL,323),(2765,NULL,'沙坡头区',NULL,324),(2766,NULL,'中宁县',NULL,324),(2767,NULL,'海原县',NULL,324),(2768,NULL,'天山区',NULL,325),(2769,NULL,'沙依巴克区',NULL,325),(2770,NULL,'新市区',NULL,325),(2771,NULL,'水磨沟区',NULL,325),(2772,NULL,'头屯河区',NULL,325),(2773,NULL,'达坂城区',NULL,325),(2774,NULL,'米东区',NULL,325),(2775,NULL,'乌鲁木齐县',NULL,325),(2776,NULL,'独山子区',NULL,326),(2777,NULL,'克拉玛依区',NULL,326),(2778,NULL,'白碱滩区',NULL,326),(2779,NULL,'乌尔禾区',NULL,326),(2780,NULL,'吐鲁番市',NULL,327),(2781,NULL,'鄯善县',NULL,327),(2782,NULL,'托克逊县',NULL,327),(2783,NULL,'哈密市',NULL,328),(2784,NULL,'巴里坤哈萨克自治县',NULL,328),(2785,NULL,'伊吾县',NULL,328),(2786,NULL,'昌吉市',NULL,329),(2789,NULL,'呼图壁县',NULL,329),(2790,NULL,'玛纳斯县',NULL,329),(2791,NULL,'奇台县',NULL,329),(2792,NULL,'吉木萨尔县',NULL,329),(2793,NULL,'木垒哈萨克自治县',NULL,329),(2794,NULL,'博乐市',NULL,330),(2795,NULL,'精河县',NULL,330),(2796,NULL,'温泉县',NULL,330),(2797,NULL,'库尔勒市',NULL,331),(2798,NULL,'轮台县',NULL,331),(2799,NULL,'尉犁县',NULL,331),(2800,NULL,'若羌县',NULL,331),(2801,NULL,'且末县',NULL,331),(2802,NULL,'焉耆回族自治县',NULL,331),(2803,NULL,'和静县',NULL,331),(2804,NULL,'和硕县',NULL,331),(2805,NULL,'博湖县',NULL,331),(2806,NULL,'阿克苏市',NULL,332),(2807,NULL,'温宿县',NULL,332),(2808,NULL,'库车县',NULL,332),(2809,NULL,'沙雅县',NULL,332),(2810,NULL,'新和县',NULL,332),(2811,NULL,'拜城县',NULL,332),(2812,NULL,'乌什县',NULL,332),(2813,NULL,'阿瓦提县',NULL,332),(2814,NULL,'柯坪县',NULL,332),(2815,NULL,'阿图什市',NULL,333),(2816,NULL,'阿克陶县',NULL,333),(2817,NULL,'阿合奇县',NULL,333),(2818,NULL,'乌恰县',NULL,333),(2819,NULL,'喀什市',NULL,334),(2820,NULL,'疏附县',NULL,334),(2821,NULL,'疏勒县',NULL,334),(2822,NULL,'英吉沙县',NULL,334),(2823,NULL,'泽普县',NULL,334),(2824,NULL,'莎车县',NULL,334),(2825,NULL,'叶城县',NULL,334),(2826,NULL,'麦盖提县',NULL,334),(2827,NULL,'岳普湖县',NULL,334),(2828,NULL,'伽师县',NULL,334),(2829,NULL,'巴楚县',NULL,334),(2830,NULL,'塔什库尔干塔吉克自治县',NULL,334),(2831,NULL,'和田市',NULL,335),(2832,NULL,'和田县',NULL,335),(2833,NULL,'墨玉县',NULL,335),(2834,NULL,'皮山县',NULL,335),(2835,NULL,'洛浦县',NULL,335),(2836,NULL,'策勒县',NULL,335),(2837,NULL,'于田县',NULL,335),(2838,NULL,'民丰县',NULL,335),(2839,NULL,'伊宁市',NULL,336),(2840,NULL,'奎屯市',NULL,336),(2841,NULL,'伊宁县',NULL,336),(2842,NULL,'察布查尔锡伯自治县',NULL,336),(2843,NULL,'霍城县',NULL,336),(2844,NULL,'巩留县',NULL,336),(2845,NULL,'新源县',NULL,336),(2846,NULL,'昭苏县',NULL,336),(2847,NULL,'特克斯县',NULL,336),(2848,NULL,'尼勒克县',NULL,336),(2849,NULL,'塔城市',NULL,337),(2850,NULL,'乌苏市',NULL,337),(2851,NULL,'额敏县',NULL,337),(2852,NULL,'沙湾县',NULL,337),(2853,NULL,'托里县',NULL,337),(2854,NULL,'裕民县',NULL,337),(2855,NULL,'和布克赛尔蒙古自治县',NULL,337),(2856,NULL,'阿勒泰市',NULL,338),(2857,NULL,'布尔津县',NULL,338),(2858,NULL,'富蕴县',NULL,338),(2859,NULL,'福海县',NULL,338),(2860,NULL,'哈巴河县',NULL,338),(2861,NULL,'青河县',NULL,338),(2862,NULL,'吉木乃县',NULL,338),(2865,NULL,'滨海新区',NULL,2),(2866,NULL,'曹妃甸区',NULL,4),(2869,NULL,'庐江县',NULL,98),(2870,NULL,'巢湖市',NULL,98),(2871,NULL,'无为县',NULL,99),(2872,NULL,'当涂县',NULL,102),(2873,NULL,'含山县',NULL,102),(2874,NULL,'和县',NULL,102),(2875,NULL,'共青城市',NULL,127),(2876,NULL,'济源市',NULL,168),(2877,NULL,'随县',NULL,180),(2878,NULL,'增城市',NULL,197),(2879,NULL,'从化市',NULL,197),(2880,NULL,'合川区',NULL,234),(2881,NULL,'永川区',NULL,234),(2882,NULL,'南川区',NULL,234),(2883,NULL,'沿河土家族自治县',NULL,260),(2884,NULL,'松桃苗族自治县',NULL,260),(2885,NULL,'金昌市',NULL,300),(2886,NULL,'红寺堡区',NULL,322);

/*Table structure for table `tbbalance` */

DROP TABLE IF EXISTS `tbbalance`;

CREATE TABLE `tbbalance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) DEFAULT NULL COMMENT '账户Id',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `dlMoney` double DEFAULT NULL COMMENT '本次金额',
  `dlBalanceMoney` double DEFAULT NULL COMMENT '当前余额',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `sourceId` int(11) DEFAULT NULL COMMENT '来源Id',
  `sourceType` int(11) DEFAULT NULL COMMENT '来源类型(0:支出、1:收入)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbbalance` */

/*Table structure for table `tbcity` */

DROP TABLE IF EXISTS `tbcity`;

CREATE TABLE `tbcity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNumber` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(20) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `proviceid` int(11) DEFAULT NULL COMMENT '所属省份ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=347 DEFAULT CHARSET=utf8;

/*Data for the table `tbcity` */

insert  into `tbcity`(`id`,`vcNumber`,`vcName`,`vcRemark`,`proviceid`) values (1,'100000','北京市',NULL,1),(2,'100000','天津市',NULL,2),(3,'050000','石家庄市',NULL,3),(4,'063000','唐山市',NULL,3),(5,'066000','秦皇岛市',NULL,3),(6,'056000','邯郸市',NULL,3),(7,'054000','邢台市',NULL,3),(8,'071000','保定市',NULL,3),(9,'075000','张家口市',NULL,3),(10,'067000','承德市',NULL,3),(11,'061000','沧州市',NULL,3),(12,'065000','廊坊市',NULL,3),(13,'053000','衡水市',NULL,3),(14,'030000','太原市',NULL,4),(15,'037000','大同市',NULL,4),(16,'045000','阳泉市',NULL,4),(17,'046000','长治市',NULL,4),(18,'048000','晋城市',NULL,4),(19,'036000','朔州市',NULL,4),(20,'030600','晋中市',NULL,4),(21,'044000','运城市',NULL,4),(22,'034000','忻州市',NULL,4),(23,'041000','临汾市',NULL,4),(24,'030500','吕梁市',NULL,4),(25,'010000','呼和浩特市',NULL,5),(26,'014000','包头市',NULL,5),(27,'016000','乌海市',NULL,5),(28,'024000','赤峰市',NULL,5),(29,'028000','通辽市',NULL,5),(30,'010300','鄂尔多斯市',NULL,5),(31,'021000','呼伦贝尔市',NULL,5),(32,'014400','巴彦淖尔市',NULL,5),(33,'011800','乌兰察布市',NULL,5),(34,'137500','兴安盟',NULL,5),(35,'011100','锡林郭勒盟',NULL,5),(36,'016000','阿拉善盟',NULL,5),(37,'110000','沈阳市',NULL,6),(38,'116000','大连市',NULL,6),(39,'114000','鞍山市',NULL,6),(40,'113000','抚顺市',NULL,6),(41,'117000','本溪市',NULL,6),(42,'118000','丹东市',NULL,6),(43,'121000','锦州市',NULL,6),(44,'115000','营口市',NULL,6),(45,'123000','阜新市',NULL,6),(46,'111000','辽阳市',NULL,6),(47,'124000','盘锦市',NULL,6),(48,'112000','铁岭市',NULL,6),(49,'122000','朝阳市',NULL,6),(50,'125000','葫芦岛市',NULL,6),(51,'130000','长春市',NULL,7),(52,'132000','吉林市',NULL,7),(53,'136000','四平市',NULL,7),(54,'136200','辽源市',NULL,7),(55,'134000','通化市',NULL,7),(56,'134300','白山市',NULL,7),(57,'131100','松原市',NULL,7),(58,'137000','白城市',NULL,7),(59,'133000','延边朝鲜族自治州',NULL,7),(60,'150000','哈尔滨市',NULL,8),(61,'161000','齐齐哈尔市',NULL,8),(62,'158100','鸡西市',NULL,8),(63,'154100','鹤岗市',NULL,8),(64,'155100','双鸭山市',NULL,8),(65,'163000','大庆市',NULL,8),(66,'152300','伊春市',NULL,8),(67,'154000','佳木斯市',NULL,8),(68,'154600','七台河市',NULL,8),(69,'157000','牡丹江市',NULL,8),(70,'164300','黑河市',NULL,8),(71,'152000','绥化市',NULL,8),(72,'165000','大兴安岭地区',NULL,8),(73,'200000','上海市',NULL,9),(74,'210000','南京市',NULL,10),(75,'214000','无锡市',NULL,10),(76,'221000','徐州市',NULL,10),(77,'213000','常州市',NULL,10),(78,'215000','苏州市',NULL,10),(79,'226000','南通市',NULL,10),(80,'222000','连云港市',NULL,10),(81,'223200','淮安市',NULL,10),(82,'224000','盐城市',NULL,10),(83,'225000','扬州市',NULL,10),(84,'212000','镇江市',NULL,10),(85,'225300','泰州市',NULL,10),(86,'223800','宿迁市',NULL,10),(87,'310000','杭州市',NULL,11),(88,'315000','宁波市',NULL,11),(89,'325000','温州市',NULL,11),(90,'314000','嘉兴市',NULL,11),(91,'313000','湖州市',NULL,11),(92,'312000','绍兴市',NULL,11),(93,'321000','金华市',NULL,11),(94,'324000','衢州市',NULL,11),(95,'316000','舟山市',NULL,11),(96,'318000','台州市',NULL,11),(97,'323000','丽水市',NULL,11),(98,'230000','合肥市',NULL,12),(99,'241000','芜湖市',NULL,12),(100,'233000','蚌埠市',NULL,12),(101,'232000','淮南市',NULL,12),(102,'243000','马鞍山市',NULL,12),(103,'235000','淮北市',NULL,12),(104,'244000','铜陵市',NULL,12),(105,'246000','安庆市',NULL,12),(106,'242700','黄山市',NULL,12),(107,'239000','滁州市',NULL,12),(108,'236100','阜阳市',NULL,12),(109,'234100','宿州市',NULL,12),(111,'237000','六安市',NULL,12),(112,'236800','亳州市',NULL,12),(113,'247100','池州市',NULL,12),(114,'366000','宣城市',NULL,12),(115,'350000','福州市',NULL,13),(116,'361000','厦门市',NULL,13),(117,'351100','莆田市',NULL,13),(118,'365000','三明市',NULL,13),(119,'362000','泉州市',NULL,13),(120,'363000','漳州市',NULL,13),(121,'353000','南平市',NULL,13),(122,'364000','龙岩市',NULL,13),(123,'352100','宁德市',NULL,13),(124,'330000','南昌市',NULL,14),(125,'333000','景德镇市',NULL,14),(126,'337000','萍乡市',NULL,14),(127,'332000','九江市',NULL,14),(128,'338000','新余市',NULL,14),(129,'335000','鹰潭市',NULL,14),(130,'341000','赣州市',NULL,14),(131,'343000','吉安市',NULL,14),(132,'336000','宜春市',NULL,14),(133,'332900','抚州市',NULL,14),(134,'334000','上饶市',NULL,14),(135,'250000','济南市',NULL,15),(136,'266000','青岛市',NULL,15),(137,'255000','淄博市',NULL,15),(138,'277100','枣庄市',NULL,15),(139,'257000','东营市',NULL,15),(140,'264000','烟台市',NULL,15),(141,'261000','潍坊市',NULL,15),(142,'272100','济宁市',NULL,15),(143,'271000','泰安市',NULL,15),(144,'265700','威海市',NULL,15),(145,'276800','日照市',NULL,15),(146,'271100','莱芜市',NULL,15),(147,'276000','临沂市',NULL,15),(148,'253000','德州市',NULL,15),(149,'252000','聊城市',NULL,15),(150,'256600','滨州市',NULL,15),(151,'255000','荷泽市',NULL,15),(152,'450000','郑州市',NULL,16),(153,'475000','开封市',NULL,16),(154,'471000','洛阳市',NULL,16),(155,'467000','平顶山市',NULL,16),(156,'454900','安阳市',NULL,16),(157,'456600','鹤壁市',NULL,16),(158,'453000','新乡市',NULL,16),(159,'454100','焦作市',NULL,16),(160,'457000','濮阳市',NULL,16),(161,'461000','许昌市',NULL,16),(162,'462000','漯河市',NULL,16),(163,'472000','三门峡市',NULL,16),(164,'473000','南阳市',NULL,16),(165,'476000','商丘市',NULL,16),(166,'464000','信阳市',NULL,16),(167,'466000','周口市',NULL,16),(168,'463000','驻马店市',NULL,16),(169,'430000','武汉市',NULL,17),(170,'435000','黄石市',NULL,17),(171,'442000','十堰市',NULL,17),(172,'443000','宜昌市',NULL,17),(173,'441000','襄阳市',NULL,17),(174,'436000','鄂州市',NULL,17),(175,'448000','荆门市',NULL,17),(176,'432100','孝感市',NULL,17),(177,'434000','荆州市',NULL,17),(178,'438000','黄冈市',NULL,17),(179,'437000','咸宁市',NULL,17),(180,'441300','随州市',NULL,17),(181,'445000','恩施土家族苗族自治州',NULL,17),(182,'442400','神农架',NULL,17),(183,'410000','长沙市',NULL,18),(184,'412000','株洲市',NULL,18),(185,'411100','湘潭市',NULL,18),(186,'421000','衡阳市',NULL,18),(187,'422000','邵阳市',NULL,18),(188,'414000','岳阳市',NULL,18),(189,'415000','常德市',NULL,18),(190,'427000','张家界市',NULL,18),(191,'413000','益阳市',NULL,18),(192,'423000','郴州市',NULL,18),(193,'425000','永州市',NULL,18),(194,'418000','怀化市',NULL,18),(195,'417000','娄底市',NULL,18),(196,'416000','湘西土家族苗族自治州',NULL,18),(197,'510000','广州市',NULL,19),(198,'521000','韶关市',NULL,19),(199,'518000','深圳市',NULL,19),(200,'519000','珠海市',NULL,19),(201,'515000','汕头市',NULL,19),(202,'528000','佛山市',NULL,19),(203,'529000','江门市',NULL,19),(204,'524000','湛江市',NULL,19),(205,'525000','茂名市',NULL,19),(206,'526000','肇庆市',NULL,19),(207,'516000','惠州市',NULL,19),(208,'514000','梅州市',NULL,19),(209,'516600','汕尾市',NULL,19),(210,'517000','河源市',NULL,19),(211,'529500','阳江市',NULL,19),(212,'511500','清远市',NULL,19),(213,'511700','东莞市',NULL,19),(214,'528400','中山市',NULL,19),(215,'515600','潮州市',NULL,19),(216,'522000','揭阳市',NULL,19),(217,'527300','云浮市',NULL,19),(218,'530000','南宁市',NULL,20),(219,'545000','柳州市',NULL,20),(220,'541000','桂林市',NULL,20),(221,'543000','梧州市',NULL,20),(222,'536000','北海市',NULL,20),(223,'538000','防城港市',NULL,20),(224,'535000','钦州市',NULL,20),(225,'537100','贵港市',NULL,20),(226,'537000','玉林市',NULL,20),(227,'533000','百色市',NULL,20),(228,'542800','贺州市',NULL,20),(229,'547000','河池市',NULL,20),(230,'546100','来宾市',NULL,20),(231,'532200','崇左市',NULL,20),(232,'570000','海口市',NULL,21),(233,'572000','三沙市',NULL,21),(234,'400000','重庆市',NULL,22),(235,'610000','成都市',NULL,23),(236,'643000','自贡市',NULL,23),(237,'617000','攀枝花市',NULL,23),(238,'646100','泸州市',NULL,23),(239,'618000','德阳市',NULL,23),(240,'621000','绵阳市',NULL,23),(241,'628000','广元市',NULL,23),(242,'629000','遂宁市',NULL,23),(243,'641000','内江市',NULL,23),(244,'614000','乐山市',NULL,23),(245,'637000','南充市',NULL,23),(246,'612100','眉山市',NULL,23),(247,'644000','宜宾市',NULL,23),(248,'638000','广安市',NULL,23),(249,'635000','达州市',NULL,23),(250,'625000','雅安市',NULL,23),(251,'635500','巴中市',NULL,23),(252,'641300','资阳市',NULL,23),(253,'624600','阿坝藏族羌族自治州',NULL,23),(254,'626000','甘孜藏族自治州',NULL,23),(255,'615000','凉山彝族自治州',NULL,23),(256,'55000','贵阳市',NULL,24),(257,'553000','六盘水市',NULL,24),(258,'563000','遵义市',NULL,24),(259,'561000','安顺市',NULL,24),(260,'554300','铜仁市',NULL,24),(261,'551500','黔西南布依族苗族自治州',NULL,24),(262,'551700','毕节市',NULL,24),(263,'551500','黔东南苗族侗族自治州',NULL,24),(264,'550100','黔南布依族苗族自治州',NULL,24),(265,'650000','昆明市',NULL,25),(266,'655000','曲靖市',NULL,25),(267,'653100','玉溪市',NULL,25),(268,'678000','保山市',NULL,25),(269,'657000','昭通市',NULL,25),(270,'674100','丽江市',NULL,25),(271,'665000','普洱市',NULL,25),(272,'677000','临沧市',NULL,25),(273,'675000','楚雄彝族自治州',NULL,25),(274,'654400','红河哈尼族彝族自治州',NULL,25),(275,'663000','文山壮族苗族自治州',NULL,25),(276,'666200','西双版纳傣族自治州',NULL,25),(277,'671000','大理白族自治州',NULL,25),(278,'678400','德宏傣族景颇族自治州',NULL,25),(279,'671400','怒江傈僳族自治州',NULL,25),(280,'674400','迪庆藏族自治州',NULL,25),(281,'850000','拉萨市',NULL,26),(282,'854000','昌都地区',NULL,26),(283,'856000','山南地区',NULL,26),(284,'857000','日喀则地区',NULL,26),(285,'852000','那曲地区',NULL,26),(286,'859100','阿里地区',NULL,26),(287,'860100','林芝地区',NULL,26),(288,'710000','西安市',NULL,27),(289,'727000','铜川市',NULL,27),(290,'721000','宝鸡市',NULL,27),(291,'712000','咸阳市',NULL,27),(292,'714000','渭南市',NULL,27),(293,'716000','延安市',NULL,27),(294,'723000','汉中市',NULL,27),(295,'719000','榆林市',NULL,27),(296,'725000','安康市',NULL,27),(297,'711500','商洛市',NULL,27),(298,'730000','兰州市',NULL,28),(300,'737100','嘉峪关市',NULL,28),(301,'730900','白银市',NULL,28),(302,'741000','天水市',NULL,28),(303,'733000','武威市',NULL,28),(304,'734000','张掖市',NULL,28),(305,'744000','平凉市',NULL,28),(306,'735000','酒泉市',NULL,28),(307,'744500','庆阳市',NULL,28),(308,'743000','定西市',NULL,28),(309,'742100','陇南市',NULL,28),(310,'731100','临夏回族自治州',NULL,28),(311,'747000','甘南藏族自治州',NULL,28),(312,'810000','西宁市',NULL,29),(313,'810600','海东地区',NULL,29),(314,'810300','海北藏族自治州',NULL,29),(315,'811300','黄南藏族自治州',NULL,29),(316,'813000','海南藏族自治州',NULL,29),(317,'814000','果洛藏族自治州',NULL,29),(318,'815000','玉树藏族自治州',NULL,29),(319,'817000','海西蒙古族藏族自治州',NULL,29),(320,'750000','银川市',NULL,30),(321,'753000','石嘴山市',NULL,30),(322,'751100','吴忠市',NULL,30),(323,'756000','固原市',NULL,30),(324,'751700','中卫市',NULL,30),(325,'830000','乌鲁木齐市',NULL,31),(326,'834000','克拉玛依市',NULL,31),(327,'838000','吐鲁番地区',NULL,31),(328,'839000','哈密地区',NULL,31),(329,'831100','昌吉回族自治州',NULL,31),(330,'833400','博尔塔拉蒙古自治州',NULL,31),(331,'841000','巴音郭楞蒙古自治州',NULL,31),(332,'843000','阿克苏地区',NULL,31),(333,'835600','克孜勒苏柯尔克孜自治州',NULL,31),(334,'844000','喀什地区',NULL,31),(335,'848000','和田地区',NULL,31),(336,'833200','伊犁哈萨克自治州',NULL,31),(337,'834700','塔城地区',NULL,31),(338,'836500','阿勒泰地区',NULL,31),(339,'832000','石河子市',NULL,31),(340,'843300','阿拉尔市',NULL,31),(341,'843900','图木舒克市',NULL,31),(342,'831300','五家渠市',NULL,31),(343,'000000','香港特别行政区',NULL,32),(344,'000000','澳门特别行政区',NULL,33),(345,'000000','台湾省',NULL,34),(346,NULL,'三亚市',NULL,21);

/*Table structure for table `tbcommodity` */

DROP TABLE IF EXISTS `tbcommodity`;

CREATE TABLE `tbcommodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '名称',
  `TypeId` int(11) DEFAULT NULL COMMENT '系列ID',
  `vcDw` varchar(10) DEFAULT NULL COMMENT '单位',
  `vcFactoryNo` varchar(20) DEFAULT NULL COMMENT '厂家编码',
  `vcFactoryName` varchar(50) DEFAULT NULL COMMENT '厂家名称',
  `vcColor` varchar(10) DEFAULT NULL COMMENT '颜色',
  `vcGg` varchar(20) DEFAULT NULL COMMENT '规格',
  `dbSuggMoney` double DEFAULT NULL COMMENT '建议售价',
  `dbLowMoney` double DEFAULT NULL COMMENT '最低售价',
  `dbAverageMoney` double DEFAULT NULL COMMENT '平均成本',
  `dbLastMoney` double DEFAULT NULL COMMENT '最后进价',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbcommodity` */

insert  into `tbcommodity`(`id`,`vcNo`,`vcName`,`TypeId`,`vcDw`,`vcFactoryNo`,`vcFactoryName`,`vcColor`,`vcGg`,`dbSuggMoney`,`dbLowMoney`,`dbAverageMoney`,`dbLastMoney`,`vcRemark`,`companyId`) values (1,'QYCS','企业测试',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL),(2,'TEST11','test11',1,'1','','','','tafasfd',NULL,NULL,NULL,NULL,'',NULL);

/*Table structure for table `tbcompany` */

DROP TABLE IF EXISTS `tbcompany`;

CREATE TABLE `tbcompany` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VC_NUMBER` varchar(50) DEFAULT NULL COMMENT '企业编号',
  `VC_NAME` varchar(50) DEFAULT NULL COMMENT '企业名称',
  `DT_BEGEINDATE` datetime DEFAULT NULL COMMENT '开户日期',
  `DT_ENDDATE` datetime DEFAULT NULL COMMENT '到期日期',
  `V_STATE` varchar(1) DEFAULT NULL COMMENT '状态(0：禁用，1：启用)',
  `VC_CITY` varchar(20) DEFAULT NULL COMMENT '区/县',
  `VC_ADDRESS` varchar(100) DEFAULT NULL COMMENT '公司地址',
  `VC_CONTACT` varchar(20) DEFAULT NULL COMMENT '联系人',
  `VC_TEL` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `VC_MOBILE` varchar(50) DEFAULT NULL COMMENT '联系手机',
  `VC_FAX` varchar(50) DEFAULT NULL COMMENT '传真',
  `VC_QQ` varchar(50) DEFAULT NULL COMMENT 'qq',
  `VC_EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `VC_WEIXIN` varchar(50) DEFAULT NULL COMMENT '微信',
  `VC_ACCOUNTNUM` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `VC_ACCOUNTNAME` varchar(50) DEFAULT NULL COMMENT '户名',
  `VC_BANK` varchar(30) DEFAULT NULL COMMENT '银行名称',
  `VC_LOGO` varchar(100) DEFAULT NULL COMMENT '企业LOGO的URL',
  `VC_REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `VC_WEB` varchar(50) DEFAULT NULL COMMENT '公司网站',
  `printInfo1` varchar(100) DEFAULT NULL COMMENT '附件资料1',
  `printInfo2` varchar(100) DEFAULT NULL COMMENT '附件资料2',
  `printInfo3` varchar(100) DEFAULT NULL COMMENT '附件资料3',
  `fwx` varchar(40) DEFAULT NULL COMMENT '微信',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbcompany` */

insert  into `tbcompany`(`ID`,`VC_NUMBER`,`VC_NAME`,`DT_BEGEINDATE`,`DT_ENDDATE`,`V_STATE`,`VC_CITY`,`VC_ADDRESS`,`VC_CONTACT`,`VC_TEL`,`VC_MOBILE`,`VC_FAX`,`VC_QQ`,`VC_EMAIL`,`VC_WEIXIN`,`VC_ACCOUNTNUM`,`VC_ACCOUNTNAME`,`VC_BANK`,`VC_LOGO`,`VC_REMARK`,`VC_WEB`,`printInfo1`,`printInfo2`,`printInfo3`,`fwx`) values (1,'测试企业','企业测试','2017-03-02 00:00:00','2017-03-15 00:00:00','1','石家庄市','','','','','','','',NULL,'','','','','','','','','','');

/*Table structure for table `tbdecorate` */

DROP TABLE IF EXISTS `tbdecorate`;

CREATE TABLE `tbdecorate` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `finoutid` int(11) DEFAULT NULL COMMENT '应收应付ID',
  `finouttype` int(11) DEFAULT NULL COMMENT '0应收 1应付',
  `fcoreid` int(11) DEFAULT NULL COMMENT '送货拼包ID',
  `fcoretype` int(11) DEFAULT NULL COMMENT '0送货 1拼包',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbdecorate` */

/*Table structure for table `tbdelivery` */

DROP TABLE IF EXISTS `tbdelivery`;

CREATE TABLE `tbdelivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salenId` int(11) DEFAULT NULL COMMENT '销售id',
  `khId` int(11) DEFAULT NULL COMMENT '客户Id',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `vcSendMan` varchar(20) DEFAULT NULL COMMENT '送货人',
  `vcAddress` varchar(200) DEFAULT NULL COMMENT '送货地址',
  `dlMoeny` double DEFAULT NULL COMMENT '金额',
  `ipayState` int(11) DEFAULT NULL COMMENT '结算状态(1:已结算、0:未结算)',
  `dtPay` varchar(15) DEFAULT NULL COMMENT '结算日期',
  `vcPayMan` varchar(20) DEFAULT NULL COMMENT '结算人',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `userId` int(11) DEFAULT NULL COMMENT '制单人Id',
  `iprintTimes` int(11) DEFAULT NULL COMMENT '打印次数',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbdelivery` */

/*Table structure for table `tbincome` */

DROP TABLE IF EXISTS `tbincome`;

CREATE TABLE `tbincome` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '编号',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `accountTypeId` int(11) DEFAULT NULL COMMENT '账目类型',
  `dlMoney` double DEFAULT NULL COMMENT '金额',
  `accountId` int(11) DEFAULT NULL COMMENT '账户',
  `vcAuditor` varchar(20) DEFAULT NULL COMMENT '经手人',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `dtWrite` varchar(15) DEFAULT NULL COMMENT '制单时间',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `sourceId` int(11) DEFAULT NULL COMMENT '来源Id',
  `fidel` int(11) DEFAULT NULL COMMENT '是否作废(0:未作废、1:已作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbincome` */

/*Table structure for table `tbinventory` */

DROP TABLE IF EXISTS `tbinventory`;

CREATE TABLE `tbinventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '编号',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `warehouseId` int(11) DEFAULT NULL COMMENT '盘点仓库',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `userId` int(11) DEFAULT NULL COMMENT '制单人',
  `istate` int(11) DEFAULT NULL COMMENT '状态(0:保存、1:已审核)',
  `checkUserId` int(11) DEFAULT NULL COMMENT '审核人',
  `dtCheck` varchar(15) DEFAULT NULL COMMENT '审核时间',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `isOk` int(11) DEFAULT NULL COMMENT '是否完成(1:已完成，0:未完成)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbinventory` */

/*Table structure for table `tbinventorydel` */

DROP TABLE IF EXISTS `tbinventorydel`;

CREATE TABLE `tbinventorydel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inventoryId` int(11) DEFAULT NULL COMMENT '主表Id',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品Id',
  `vcBatch` varchar(20) DEFAULT NULL COMMENT '批次',
  `vcSn` varchar(20) DEFAULT NULL COMMENT '辅助标识',
  `warehouseId` int(11) DEFAULT NULL COMMENT '存放库存',
  `dlBefore` double DEFAULT NULL COMMENT '账面数量(程序库存)',
  `dlActual` double DEFAULT NULL COMMENT '实际数量(清点数量)',
  `stockId` int(11) DEFAULT NULL COMMENT '库存的Id(清点的库存Id)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbinventorydel` */

/*Table structure for table `tblogistics` */

DROP TABLE IF EXISTS `tblogistics`;

CREATE TABLE `tblogistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tblogistics` */

/*Table structure for table `tblogisticscompany` */

DROP TABLE IF EXISTS `tblogisticscompany`;

CREATE TABLE `tblogisticscompany` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `vctype` int(11) DEFAULT NULL COMMENT '类型 0：快递、1：物流',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tblogisticscompany` */

/*Table structure for table `tboutcome` */

DROP TABLE IF EXISTS `tboutcome`;

CREATE TABLE `tboutcome` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '编号',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `accountTypeId` int(11) DEFAULT NULL COMMENT '账目类型',
  `dlMoney` double DEFAULT NULL COMMENT '金额',
  `accountId` int(11) DEFAULT NULL COMMENT '账户',
  `vcAuditor` varchar(20) DEFAULT NULL COMMENT '经手人',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `dtWrite` varchar(15) DEFAULT NULL COMMENT '制单日期',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `sourceId` int(11) DEFAULT NULL COMMENT '来源Id',
  `fidel` int(11) DEFAULT NULL COMMENT '是否作废(0:未作废、1:已作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tboutcome` */

/*Table structure for table `tbperson` */

DROP TABLE IF EXISTS `tbperson`;

CREATE TABLE `tbperson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '员工编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '员工名称',
  `iGender` int(11) DEFAULT NULL COMMENT '性别(0、女,1、男)',
  `vcNation` varchar(20) DEFAULT NULL COMMENT '民族',
  `vcIdCard` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `vcAddress` varchar(100) DEFAULT NULL COMMENT '现居住地',
  `positionId` int(11) DEFAULT NULL COMMENT '职位ID',
  `iComminSsion` int(11) DEFAULT NULL COMMENT '是否有提成(0、否,1、是)',
  `vcTel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `iState` int(11) DEFAULT NULL COMMENT '状态(1、在职,0、离职)',
  `dtEntry` varchar(15) DEFAULT NULL COMMENT '入职日期',
  `dtQuit` varchar(15) DEFAULT NULL COMMENT '离职日期',
  `vcQuitReason` varchar(200) DEFAULT NULL COMMENT '离职原因',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbperson` */

/*Table structure for table `tbposition` */

DROP TABLE IF EXISTS `tbposition`;

CREATE TABLE `tbposition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbposition` */

/*Table structure for table `tbprovince` */

DROP TABLE IF EXISTS `tbprovince`;

CREATE TABLE `tbprovince` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNumber` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(20) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `tbprovince` */

insert  into `tbprovince`(`id`,`vcNumber`,`vcName`,`vcRemark`) values (1,NULL,'北京市',NULL),(2,NULL,'天津市',NULL),(3,NULL,'河北省',NULL),(4,NULL,'山西省',NULL),(5,NULL,'内蒙古自治区',NULL),(6,NULL,'辽宁省',NULL),(7,NULL,'吉林省',NULL),(8,NULL,'黑龙江省',NULL),(9,NULL,'上海市',NULL),(10,NULL,'江苏省',NULL),(11,NULL,'浙江省',NULL),(12,NULL,'安徽省',NULL),(13,NULL,'福建省',NULL),(14,NULL,'江西省',NULL),(15,NULL,'山东省',NULL),(16,NULL,'河南省',NULL),(17,NULL,'湖北省',NULL),(18,NULL,'湖南省',NULL),(19,NULL,'广东省',NULL),(20,NULL,'广西壮族自治区',NULL),(21,NULL,'海南省',NULL),(22,NULL,'重庆市',NULL),(23,NULL,'四川省',NULL),(24,NULL,'贵州省',NULL),(25,NULL,'云南省',NULL),(26,NULL,'西藏自治区',NULL),(27,NULL,'陕西省',NULL),(28,NULL,'甘肃省',NULL),(29,NULL,'青海省',NULL),(30,NULL,'宁夏回族自治区',NULL),(31,NULL,'新疆维吾尔自治区',NULL),(32,NULL,'香港特别行政区',NULL),(33,NULL,'澳门特别行政区',NULL),(34,NULL,'台湾省',NULL);

/*Table structure for table `tbreturn` */

DROP TABLE IF EXISTS `tbreturn`;

CREATE TABLE `tbreturn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(15) DEFAULT NULL COMMENT '编号',
  `gysId` int(11) DEFAULT NULL COMMENT '供应商',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `dtReceived` varchar(15) DEFAULT NULL COMMENT '收货日期',
  `deShouldPayMoney` double DEFAULT NULL COMMENT '应付金额',
  `deActualPayMoney` double DEFAULT NULL COMMENT '实付金额',
  `iPayState` int(11) DEFAULT NULL COMMENT '付款状态(1:已付款、2:未付款)',
  `userId` int(11) DEFAULT NULL COMMENT '录入人',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `icbill` int(11) DEFAULT NULL COMMENT '生成应付单状态(0:未生成、1:已生成)',
  `fidel` int(11) DEFAULT NULL COMMENT '是否已作废(0:未作废、1:已作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbreturn` */

/*Table structure for table `tbreturndel` */

DROP TABLE IF EXISTS `tbreturndel`;

CREATE TABLE `tbreturndel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `returnId` int(11) DEFAULT NULL COMMENT '退货单ID',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品ID',
  `dePurchaseMoney` double DEFAULT NULL COMMENT '进货价格',
  `iCount` double DEFAULT NULL COMMENT '数量',
  `deSumMoney` double DEFAULT NULL COMMENT '总金额',
  `vcDw` varchar(10) DEFAULT NULL COMMENT '单位',
  `vcColor` varchar(10) DEFAULT NULL COMMENT '颜色',
  `vcBatch` varchar(50) DEFAULT NULL COMMENT '批次',
  `vcSn` varchar(50) DEFAULT NULL COMMENT '辅助标识',
  `warehouseId` int(11) DEFAULT NULL COMMENT '所属仓库',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbreturndel` */

/*Table structure for table `tbsalenback` */

DROP TABLE IF EXISTS `tbsalenback`;

CREATE TABLE `tbsalenback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '单据编号',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `khId` int(11) DEFAULT NULL COMMENT '客户Id',
  `ipayType` int(11) DEFAULT NULL COMMENT '是否付款(0:未付、1:已付)',
  `deMoney` double DEFAULT NULL COMMENT '应付',
  `deOkMoney` double DEFAULT NULL COMMENT '实付',
  `deOtherMoney` double DEFAULT NULL COMMENT '其他金额',
  `warehouseId` int(11) DEFAULT NULL COMMENT '仓库',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `userId` int(11) DEFAULT NULL COMMENT '制单人',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `icbill` int(11) DEFAULT NULL COMMENT '是否生成应收单(0:未生成、1:已生成)',
  `fidel` int(11) DEFAULT NULL COMMENT '是否已作废(0:未作废、1:已作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbsalenback` */

/*Table structure for table `tbsalenbackdel` */

DROP TABLE IF EXISTS `tbsalenbackdel`;

CREATE TABLE `tbsalenbackdel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salenbackId` int(11) DEFAULT NULL COMMENT '销退表',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品表',
  `vcBatch` varchar(20) DEFAULT NULL COMMENT '批次',
  `vcSn` varchar(20) DEFAULT NULL COMMENT '辅助标识',
  `warehouseId` int(11) DEFAULT NULL COMMENT '仓库',
  `iCount` double DEFAULT NULL COMMENT '数量(米)',
  `vcDw` varchar(10) DEFAULT NULL COMMENT '单位',
  `dePriceMoney` double DEFAULT NULL COMMENT '单价',
  `deSumMoney` double DEFAULT NULL COMMENT '总金额',
  `deDiscount` double DEFAULT NULL COMMENT '优惠',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbsalenbackdel` */

/*Table structure for table `tbsalenout` */

DROP TABLE IF EXISTS `tbsalenout`;

CREATE TABLE `tbsalenout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '单据编号',
  `dtBs` varchar(20) DEFAULT NULL COMMENT '业务日期',
  `iKh` int(11) DEFAULT NULL COMMENT '客户',
  `vcTel` varchar(50) DEFAULT NULL COMMENT '电话',
  `iSaleType` int(11) DEFAULT NULL COMMENT '销售类型(0、批发,1、零售)',
  `iSettlement` int(11) DEFAULT NULL COMMENT '结算方式',
  `iPay` int(11) DEFAULT NULL COMMENT '是否付款(0、未付,1、已付)',
  `userId` int(11) DEFAULT NULL COMMENT '制单人',
  `vcAddress` varchar(200) DEFAULT NULL COMMENT '地址',
  `iLogistics` int(11) DEFAULT NULL COMMENT '配送方式',
  `iwl` int(11) DEFAULT NULL COMMENT '物流公司',
  `vcYunNo` varchar(50) DEFAULT NULL COMMENT '运单号',
  `deOutMoney` double DEFAULT NULL COMMENT '应付金额',
  `deDiscount` double DEFAULT NULL COMMENT '折扣率',
  `deOkOutMoney` double DEFAULT NULL COMMENT '实付金额',
  `deOweMoney` double DEFAULT NULL COMMENT '上欠',
  `deOtherMoeny` double DEFAULT NULL COMMENT '其他金额',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `iSaleprint` int(11) DEFAULT NULL COMMENT '是否打印(0、不打印,1、打印)',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `icbill` int(11) DEFAULT NULL COMMENT '是否已生成应收单(0:未生成、1:已生成)',
  `fidel` int(11) DEFAULT NULL COMMENT '是否作废(1:已作废、0:未作废)',
  `vcPhone` varchar(50) DEFAULT NULL COMMENT '客户的手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbsalenout` */

/*Table structure for table `tbsalenoutdel` */

DROP TABLE IF EXISTS `tbsalenoutdel`;

CREATE TABLE `tbsalenoutdel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salenoutId` int(11) DEFAULT NULL COMMENT '销售出库表Id',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品Id',
  `vcBatch` varchar(20) DEFAULT NULL COMMENT '批次',
  `vcSn` varchar(20) DEFAULT NULL COMMENT '辅助标识',
  `warehouseId` int(11) DEFAULT NULL COMMENT '仓库Id',
  `iCount` double DEFAULT NULL COMMENT '数量(米)',
  `vcDw` varchar(10) DEFAULT NULL COMMENT '单位',
  `dePriceMoney` double DEFAULT NULL COMMENT '单价',
  `deSumMoney` double DEFAULT NULL COMMENT '总金额',
  `deDiscount` double DEFAULT NULL COMMENT '优惠',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbsalenoutdel` */

/*Table structure for table `tbsettlement` */

DROP TABLE IF EXISTS `tbsettlement`;

CREATE TABLE `tbsettlement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbsettlement` */

/*Table structure for table `tbspellpack` */

DROP TABLE IF EXISTS `tbspellpack`;

CREATE TABLE `tbspellpack` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salenId` int(11) DEFAULT NULL COMMENT '销售Id',
  `khId` int(11) DEFAULT NULL COMMENT '客户Id',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `vcSpllName` varchar(20) DEFAULT NULL COMMENT '拼包人',
  `dlMoney` double DEFAULT NULL COMMENT '金额',
  `ipayState` int(11) DEFAULT NULL COMMENT '结算状态(1:已结算，0:未结算)',
  `dtPay` varchar(15) DEFAULT NULL COMMENT '结算日期',
  `vcPayMan` varchar(30) DEFAULT NULL COMMENT '结算人',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `userId` int(11) DEFAULT NULL COMMENT '制单人',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbspellpack` */

/*Table structure for table `tbstock` */

DROP TABLE IF EXISTS `tbstock`;

CREATE TABLE `tbstock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodityId` int(11) DEFAULT NULL COMMENT '关联商品表',
  `warehouseId` int(11) DEFAULT NULL COMMENT '关联仓库表',
  `vcBatch` varchar(50) DEFAULT NULL COMMENT '批次',
  `vcSn` varchar(50) DEFAULT NULL COMMENT '辅助标识',
  `dlQty` double DEFAULT NULL COMMENT '数量(米)',
  `vcDw` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbstock` */

/*Table structure for table `tbstorage` */

DROP TABLE IF EXISTS `tbstorage`;

CREATE TABLE `tbstorage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(15) DEFAULT NULL COMMENT '进货单号',
  `gysId` int(11) DEFAULT NULL COMMENT '供应商ID',
  `dtBs` varchar(15) DEFAULT NULL COMMENT '业务日期',
  `dtReceived` varchar(15) DEFAULT NULL COMMENT '收货日期',
  `deShouldPayMoney` double DEFAULT NULL COMMENT '应付金额',
  `deActualPayMoney` double DEFAULT NULL COMMENT '实付金额',
  `iPayState` int(11) DEFAULT NULL COMMENT '付款状态(0:未付,1:已付)',
  `userId` int(11) DEFAULT NULL COMMENT '录入人ID',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  `icbill` int(11) DEFAULT NULL COMMENT '生成应付单状态(0:未生成、1:已生成)',
  `fidel` int(11) DEFAULT NULL COMMENT '是否作废(0:已作废、1:未作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbstorage` */

/*Table structure for table `tbstoragedel` */

DROP TABLE IF EXISTS `tbstoragedel`;

CREATE TABLE `tbstoragedel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `storageId` int(11) DEFAULT NULL COMMENT '进货单ID',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品Id',
  `dePurchaseMoney` double DEFAULT NULL COMMENT '进货价格',
  `iCount` double DEFAULT NULL COMMENT '数量(米)',
  `deSumMoney` double DEFAULT NULL COMMENT '总金额',
  `vcDw` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `vcColor` varchar(10) DEFAULT NULL COMMENT '颜色',
  `vcBatch` varchar(50) DEFAULT NULL COMMENT '批次',
  `vcSn` varchar(50) DEFAULT NULL COMMENT '辅助标识',
  `warehouseId` int(11) DEFAULT NULL COMMENT '所属仓库',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbstoragedel` */

/*Table structure for table `tbtemplate` */

DROP TABLE IF EXISTS `tbtemplate`;

CREATE TABLE `tbtemplate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itype` int(11) DEFAULT NULL COMMENT '类型(1：打印、2：报表)',
  `vcNo` varchar(20) DEFAULT NULL COMMENT '报表编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '报表名称',
  `vcTable` varchar(20) DEFAULT NULL COMMENT '所属表',
  `vccode` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `vcRemark` varchar(200) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbtemplate` */

/*Table structure for table `tbunit` */

DROP TABLE IF EXISTS `tbunit`;

CREATE TABLE `tbunit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(10) DEFAULT NULL COMMENT '编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '名称',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbunit` */

/*Table structure for table `tbuserunitrights` */

DROP TABLE IF EXISTS `tbuserunitrights`;

CREATE TABLE `tbuserunitrights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '用户ID',
  `companyid` int(11) DEFAULT NULL COMMENT '企业ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbuserunitrights` */

insert  into `tbuserunitrights`(`id`,`userid`,`companyid`) values (1,2,1);

/*Table structure for table `tbwarehouse` */

DROP TABLE IF EXISTS `tbwarehouse`;

CREATE TABLE `tbwarehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vcNo` varchar(20) DEFAULT NULL COMMENT '仓库编号',
  `vcName` varchar(50) DEFAULT NULL COMMENT '仓库名称',
  `vcAddress` varchar(200) DEFAULT NULL COMMENT '仓库地址',
  `vcRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `companyId` int(11) DEFAULT NULL COMMENT '所属企业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbwarehouse` */

/*Table structure for table `thd` */

DROP TABLE IF EXISTS `thd`;

CREATE TABLE `thd` (
  `djid` varchar(14) NOT NULL,
  `gysid` int(11) DEFAULT NULL,
  `gysname` varchar(50) DEFAULT NULL,
  `riqi` datetime NOT NULL,
  `yfje` double DEFAULT NULL COMMENT 'Ӧ',
  `sfje` double DEFAULT NULL COMMENT 'ʵ',
  `jystate` varchar(2) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`djid`),
  KEY `FK1C070A2220F2D` (`gysid`),
  KEY `FK1C070532696FE` (`userid`),
  CONSTRAINT `FK1C070532696FE` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`),
  CONSTRAINT `FK1C070A2220F2D` FOREIGN KEY (`gysid`) REFERENCES `gys` (`gysid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `thd` */

/*Table structure for table `thdsp` */

DROP TABLE IF EXISTS `thdsp`;

CREATE TABLE `thdsp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `djid` varchar(14) NOT NULL,
  `spid` varchar(10) NOT NULL,
  `spname` varchar(20) DEFAULT NULL,
  `spdw` varchar(20) DEFAULT NULL,
  `spxinghao` varchar(20) DEFAULT NULL,
  `lbid` int(11) DEFAULT NULL,
  `lbname` varchar(20) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `zj` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK69372CD9C6D1B61` (`djid`),
  CONSTRAINT `FK69372CD9C6D1B61` FOREIGN KEY (`djid`) REFERENCES `thd` (`djid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `thdsp` */

/*Table structure for table `tkd` */

DROP TABLE IF EXISTS `tkd`;

CREATE TABLE `tkd` (
  `djid` varchar(14) NOT NULL,
  `khid` int(11) DEFAULT NULL,
  `khname` varchar(50) DEFAULT NULL,
  `riqi` datetime NOT NULL,
  `yfje` double DEFAULT NULL,
  `sfje` double DEFAULT NULL,
  `cbje` double DEFAULT NULL,
  `jystate` varchar(2) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`djid`),
  KEY `FK1C0CD78D95FC5` (`khid`),
  KEY `FK1C0CD532696FE` (`userid`),
  CONSTRAINT `FK1C0CD532696FE` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`),
  CONSTRAINT `FK1C0CD78D95FC5` FOREIGN KEY (`khid`) REFERENCES `kh` (`khid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tkd` */

/*Table structure for table `tkdsp` */

DROP TABLE IF EXISTS `tkdsp`;

CREATE TABLE `tkdsp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `djid` varchar(14) NOT NULL,
  `spid` varchar(10) NOT NULL,
  `spname` varchar(20) DEFAULT NULL,
  `spdw` varchar(20) DEFAULT NULL,
  `spxinghao` varchar(20) DEFAULT NULL,
  `lbid` int(11) DEFAULT NULL,
  `lbname` varchar(20) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `zj` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK694CFEA9C6D1BBE` (`djid`),
  CONSTRAINT `FK694CFEA9C6D1BBE` FOREIGN KEY (`djid`) REFERENCES `tkd` (`djid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tkdsp` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `companyid` int(11) DEFAULT NULL,
  `logincode` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `state` int(11) NOT NULL,
  `bz` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `FK6A68E08DF13C377` (`companyid`),
  KEY `FK6A68E08B459D7B7` (`roleid`),
  CONSTRAINT `FK6A68E08B459D7B7` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`),
  CONSTRAINT `FK6A68E08DF13C377` FOREIGN KEY (`companyid`) REFERENCES `tbcompany` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`userid`,`roleid`,`companyid`,`logincode`,`password`,`username`,`state`,`bz`) values (1,1,NULL,'root','000000','root',1,NULL),(2,5,1,'test','000000','test',0,'');

/*Table structure for table `vusermenu` */

DROP TABLE IF EXISTS `vusermenu`;

CREATE TABLE `vusermenu` (
  `userid` int(11) NOT NULL,
  `menuid` int(11) NOT NULL,
  `logincode` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `menuname` varchar(20) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `menuurl` varchar(100) DEFAULT NULL,
  `menutype` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `icon` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`,`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vusermenu` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
