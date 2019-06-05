-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: child_ill_system
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `vaccine`
--

DROP TABLE IF EXISTS `vaccine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vaccine` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `pathogenesis` text,
  `precaution` text,
  `handling` text,
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaccine`
--

LOCK TABLES `vaccine` WRITE;
/*!40000 ALTER TABLE `vaccine` DISABLE KEYS */;
INSERT INTO `vaccine` VALUES (1,'乙型炎疫苗（乙肝）','又称乙肝，临床上以食欲减退、恶心、上腹部不适、肝区痛、乏力为主要表现。部分患者可有黄疸发热和肝大伴有肝功能损害。有些患者可慢性化，甚至发展成肝硬化，少数可发展为肝癌。','乙型肝炎病毒最主要通过血液传播，因而最重要的传播方式是母婴垂直传播和医源性感染，预防措施为：1.管理传染源、2.切断传播途径','乙肝疫苗接种是有效控制HBV传播的必要手段，目前中国实行新生儿强制计划免疫，一出生就接种乙肝疫苗。接种疫苗后有抗体应答者的保护效果一般至少可持续12年.'),(2,'卡介苗','接种卡介苗后可使儿童产生对结核病的抵抗力，尤其是大大降低粟粒性结核病和结核性脑膜炎的发病率。还可用于肿瘤的辅助治疗以及治疗哮喘性支气管炎及预防小儿感冒。','1.严禁皮下或肌内注射。','有活动性结核病的患者禁用。以下情况者慎用：家族和个人有惊厥史者、患慢性疾病者、有癫痫史者、过敏体质者、哺乳期妇女。'),(3,'麻疹疫苗','持续性发热，咽痛，畏光，流泪，眼结膜红肿等。在口腔颊黏膜处见到麻疹黏膜斑。','在接触麻疹后5天内立即给于免疫血清球蛋白，可预防麻疹发病；超过6天则无法达到上述效果。使用过免疫血清球蛋白者的临床过程变化大，潜伏期长，症状、体征不典型，但对接触者仍有潜在传染性。被动免疫只能维持8周，以后应采取主动免疫措施。','要做到早期发现，早期隔离。一般病人隔离至出疹后5天，合并肺炎者延长至10天。接触麻疹的易感者应检疫观察3周。病人衣物应在阳光下曝晒，病人曾住房间宜通风并用紫外线照射，流行季节中做好宣传工作，易感儿尽量少去公共场所。'),(4,'脊髓灰质炎疫苗（加强）','临床表现主要以发热、上呼吸道症状、肢体疼痛为主。病毒主要侵犯人体脊髓灰质前角的灰、白质部分，对灰质造成永久性损害，出现肢体弛缓性麻痹。部分患者可发生迟缓性神经麻痹并留下瘫痪后遗症，一般多见于5岁以下小儿，故俗称“小儿麻痹症”。','1.注射免疫球蛋白应至少间隔3个月以上接种本疫苗，以免影响免疫效果。2.使用不同的减毒活疫苗进行预防接种时，应间隔至少1个月以上。3.高烧、免疫能力受损、正使用肾上腺皮质激素或抗癌药物治疗者不宜服用脊髓灰质炎疫苗。4.一旦发病，应及时去医院隔离治疗，并报告卫生防疫站，密切接触者医学观察20天，对未服过疫苗或服疫苗不全者应立即补服。','1.最好在服苗后半小时内停止吸吮母乳（可用牛奶或其他代乳品），否则母乳中抗体会中和（杀死）疫苗，影响效果。2.如果在服用时出现呕吐应重服。'),(5,'流感疫苗','可以表现为高热、全身酸痛、头痛、乏力、眼结膜炎等中毒症状。集中在每年11月份至次年3月份中旬。流感起病急骤、传播快、发病率高，传染率可达50%，常可引起爆发流行','生活中还应注意保持室内空气流通，注意环境清洁；保证充足睡眠、健康饮食并坚持适量运动；注意天气变化，随气温变化增减衣物；勤换衣、勤洗手，打喷嚏或咳嗽时用纸巾或袖子遮住口、鼻；生活、工作、学习中应尽量避免接触流感患者，若需接触则应佩戴口罩，做好防护；减少聚集活动','专家指出，接种流感疫苗是个人预防流感的最有效手段'),(6,'狂犬病疫苗','凡被病兽或带毒动物咬伤或抓伤后，应立即注射狂犬疫苗。若被严重咬伤，如伤口在头面部、全身多部位咬伤、深度咬伤等，应联合用抗狂犬病毒血清。','少接触流浪动物或者未打预防针的动物','24小时内到医院打狂犬病疫苗'),(7,'水痘疫苗','以发热及皮肤和黏膜成批出现周身性红色斑丘疹、疱疹、痂疹为特征，皮疹呈向心性分布，主要发生在胸、腹、背，四肢很少。发热与斑丘疹、疱疹同时，或无发热即出疹，传染性强','控制感染源，隔离患儿至皮疹全部结痂为止，对已接触的易感儿，应检疫3周。对免疫功能低下、应用免疫抑制剂者及孕妇，若有接触史，可使用丙种球蛋白，或带状疱疹免疫球蛋白，肌肉注射。水痘减毒活疫苗是第一种在许多国家被批准临床应用的人类疱疹病毒疫苗，接种后的随访观察发现水痘疫苗对接种者具有较好的保护率。','局部治疗以止痒和防止感染为主，可外搽炉甘石洗剂，疱疹破溃或继发感染者可外用1%甲紫或抗生素软膏。继发感染全身症状严重时，可用抗生素。'),(9,'百白破疫苗','123456','12358','123456'),(10,'乙脑疫苗','小孩预防xxxxxxxx','小孩预防xxxxxxxx','小孩预防xxxxxxxx'),(11,'H1N1','等等','注意发烧','打疫苗');
/*!40000 ALTER TABLE `vaccine` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-05 17:00:25
