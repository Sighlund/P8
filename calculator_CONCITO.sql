-- MySQL dump 10.13  Distrib 8.0.23, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: calculator
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CONCITO`
--

DROP TABLE IF EXISTS `CONCITO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CONCITO` (
  `concitoName` varchar(100) DEFAULT NULL,
  `concitoCategory` varchar(100) DEFAULT NULL,
  `concitoC02PrKg` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONCITO`
--

LOCK TABLES `CONCITO` WRITE;
/*!40000 ALTER TABLE `CONCITO` DISABLE KEYS */;
INSERT INTO `CONCITO` VALUES ('Oksekød, mørbrad, afpudset, rå','Kød/fjerkræ',151.95),('Oksekød, tyndsteg med mørbrad, rå','Kød/fjerkræ',80.89),('Kalvekød, middelfedt, råt','Kød/fjerkræ',50.36),('Roastbeef, pålæg','Kød/fjerkræ',45.84),('Oksekød, inderlår uden kappe, rå','Kød/fjerkræ',45.69),('Oksekød, culotte, rå','Kød/fjerkræ',45.69),('Pulled beef','Kød/fjerkræ',38.22),('Kebab','Kød/fjerkræ',36.49),('Oksekød, lavt fedtindhold','Kød/fjerkræ',35.86),('Oksekød, hakket, 5-10% fedt, rå','Kød/fjerkræ',34.19),('Spegepølse, oksekød','Kød/fjerkræ',34.11),('Oksekød, hakket, 10-15% fedt, rå','Kød/fjerkræ',32.51),('Oksekød, hakket, 15-20% fedt, rå','Kød/fjerkræ',30.84),('Lammekød, kølle, uspec., rå','Kød/fjerkræ',27.43),('Lammekød, uspec., råt','Kød/fjerkræ',27.43),('Hakket lammekød','Kød/fjerkræ',26.3),('Rullepølse, lammekød, pålæg','Kød/fjerkræ',24.75),('Chili con carne, færdigretter','Tilberedte/konserverede fødevarer',21.07),('Forloren skildpadde, uspec., færdigretter','Kød/fjerkræ',18.49),('Brasen, rå','Fisk og skaldyr',16.78),('Torsk, lever, konserves','Fisk og skaldyr',16.72),('Kalv og flæsk, hakket, 15-20% fedt, råt','Kød/fjerkræ',15.26),('Krabbekløer, rå','Fisk og skaldyr',14.68),('Aborre, rå','Fisk og skaldyr',13.84),('Millionbøf, uspec., færdigretter','Kød/fjerkræ',13.74),('Biksemad, okse, færdigretter','Tilberedte/konserverede fødevarer',13.21),('Knude, rå','Fisk og skaldyr',12.54),('Helt, rå','Fisk og skaldyr',12.54),('Torsk, lever, røget','Fisk og skaldyr',12.54),('Reje, sort tiger-, opdræt, kogt, dybfrost','Fisk og skaldyr',12.43),('Bækforel, rå','Fisk og skaldyr',12.31),('Laks, atlantisk, opdræt, varmrøget','Fisk og skaldyr',12.2),('Laks, atlantisk, opdræt, koldrøget','Fisk og skaldyr',11.89),('Rejer, dybvands-, konserves','Fisk og skaldyr',11.89),('Hellefisk, røget','Fisk og skaldyr',11.59),('Kødboller, konserves','Tilberedte/konserverede fødevarer',11.42),('Kødboller, uspec.','Kød/fjerkræ',11.42),('Torsk, rogn, konserves','Fisk og skaldyr',11.36),('Kaviar, uægte (stenbiderrogn)','Fisk og skaldyr',11.33),('Kaviar, uægte (lodderogn)','Fisk og skaldyr',11.32),('Torsk, rogn, rå','Fisk og skaldyr',11.27),('Kødboller, dybfrost','Tilberedte/konserverede fødevarer',11.08),('Rejer, dybvands-, kogt, dybfrost','Fisk og skaldyr',10.52),('Reje, dybvands, kogt, i lage','Fisk og skaldyr',10.52),('Fiskefrikadelle','Fisk og skaldyr',10.28),('Tun, rå','Fisk og skaldyr',10.14),('Kaffe, instant, pulver','Drikkevarer',10.11),('Torsk, lever, rå','Fisk og skaldyr',9.92),('Rødspætte, rå','Fisk og skaldyr',9.87),('Mørksej, rå','Fisk og skaldyr',9.87),('Torsk, filet, rå','Fisk og skaldyr',9.87),('Hummer, konserves','Fisk og skaldyr',9.85),('Sej, filet, dybfrost','Fisk og skaldyr',9.78),('Hornfisk, rå','Fisk og skaldyr',9.7),('Laks, atlantisk, vild, rå','Fisk og skaldyr',9.46),('Biksemad, blandet kød, færdigretter','Tilberedte/konserverede fødevarer',9.42),('Sild, rå','Fisk og skaldyr',9.34),('Tun i tomat, konserves','Fisk og skaldyr',9.15),('Makrel, rå','Fisk og skaldyr',9.12),('Laks, atlantisk, opdræt, rå','Fisk og skaldyr',9.11),('Lasagne, oksekød, færdigretter','Tilberedte/konserverede fødevarer',8.85),('Te, blade','Drikkevarer',8.41),('Gedde, rå','Fisk og skaldyr',8.28),('Cognac','Drikkevarer',8.22),('Fiskefars, rå','Fisk og skaldyr',8),('Danbo ost, 45+','Mælk/æg/erstatningsprodukter',7.72),('Parmesan ost, 32+','Mælk/æg/erstatningsprodukter',7.72),('Mozzarella ost, 30+','Mælk/æg/erstatningsprodukter',7.72),('Danablu ost 50+','Mælk/æg/erstatningsprodukter',7.72),('Flødeost, ost 60+','Mælk/æg/erstatningsprodukter',7.72),('Fiskefilet, paneret, friturestegt','Fisk og skaldyr',7.51),('Sandart, rå','Fisk og skaldyr',7.32),('Bouillon, oksekød, koncentreret, terning','Krydderier/konserveringsmidler/ekstrakter',7.26),('Rødspætte, filet, paneret, dybfrost','Fisk og skaldyr',6.66),('Torsk, filet, paneret, rå','Fisk og skaldyr',6.63),('Fiskepinde, panerede, dybfrost','Fisk og skaldyr',6.59),('Marcipan','Slik/sukkervarer',6.25),('Chorizo, skivet','Kød/fjerkræ',6.07),('Tyggegummi, uden sukker, uspec.','Slik/sukkervarer',5.96),('Krabbe, kogt','Fisk og skaldyr',5.9),('Fiskeboller, konserves','Fisk og skaldyr',5.74),('Tun i vand, konserves','Fisk og skaldyr',5.6),('Grisemørbrad, afpudset, rå','Kød/fjerkræ',5.4),('Chokolade, fløde','Slik/sukkervarer',5.18),('Pålægschokolade','Slik/sukkervarer',5.18),('Salami','Kød/fjerkræ',5.06),('Pepperoni','Kød/fjerkræ',5.06),('Kartoffelmos, pulver med tørmælk','Frugt/grøntsagsprodukter',5),('Kakao, instant, uden mælk, pulver','Drikkevarer',4.98),('Kakao, pulver','Drikkevarer',4.98),('Nougat','Brød/bageartikler',4.97),('Dressing, thousand island','Krydderier/konserveringsmidler/ekstrakter',4.86),('Bacon, stegestykke, rå','Kød/fjerkræ',4.8),('Karry, pulver','Krydderier/konserveringsmidler/ekstrakter',4.7),('Tunsalat','Krydderier/konserveringsmidler/ekstrakter',4.5),('Fennikel, knold, rå','Grøntsager',4.46),('Basilikum, tørret','Krydderier/konserveringsmidler/ekstrakter',4.44),('Gedemælk','Mælk/æg/erstatningsprodukter',4.4),('Hasselnød, tørret','Frugt/grøntsagsprodukter',4.35),('Peber, sort','Krydderier/konserveringsmidler/ekstrakter',4.3),('Løg, ristede','Frugt/grøntsagsprodukter',4.3),('Sherry, tør','Drikkevarer',4.27),('Grisefilet, helt afpudset, rå','Kød/fjerkræ',4.19),('Makrelsalat, mayonnaise','Krydderier/konserveringsmidler/ekstrakter',4.18),('Kartoffelchips, grove','Tilberedte/konserverede fødevarer',4.17),('Kartoffel, chips (franske kartofler)','Tilberedte/konserverede fødevarer',4.17),('Forårsruller, oksekød, færdigretter','Tilberedte/konserverede fødevarer',4.14),('Tahin, sesampasta','Krydderier/konserveringsmidler/ekstrakter',4.08),('Dressing, olie-eddike','Krydderier/konserveringsmidler/ekstrakter',4.02),('Cashewnødder, tørristet','Frugt/grøntsagsprodukter',4.01),('Nøddepasta med cacao, smørepålæg','Slik/sukkervarer',3.97),('Nutella, nøddecreme','Slik/sukkervarer',3.97),('Smør, saltet','Mælk/æg/erstatningsprodukter',3.92),('Pizza med kød, tomat og ost, færdigretter','Brød/bageartikler',3.91),('Pizza med kød og fisk, tomat og ost, færdigretter','Brød/bageartikler',3.85),('Rapsolie','Spiseolie/-fedt',3.84),('Olivenolie','Spiseolie/-fedt',3.83),('Pesto','Krydderier/konserveringsmidler/ekstrakter',3.83),('Grisekød i karrysovs, dybfrost, færdigretter','Kød/fjerkræ',3.82),('Smørbart blandingsprodukt, 75% fedt','Mælk/æg/erstatningsprodukter',3.8),('Pizza med fisk, tomat og ost, færdigretter','Brød/bageartikler',3.79),('Solsikkeolie','Spiseolie/-fedt',3.76),('Hakket kylling','Kød/fjerkræ',3.76),('Chokolade, mørk','Slik/sukkervarer',3.74),('Pulled pork','Kød/fjerkræ',3.68),('Sesamfrø, afskallede','Frugt/grøntsagsprodukter',3.62),('Kylling, grillstegt, fastfood','Tilberedte/konserverede fødevarer',3.61),('Grisekød, nakkekam med svær, rå','Kød/fjerkræ',3.58),('Skinke, inderlår, helt afpudset, rå','Kød/fjerkræ',3.58),('Grisekød, nakkefilet, helt afpudset (Nakkekotelet), rå','Kød/fjerkræ',3.57),('Krebs, rå','Fisk og skaldyr',3.55),('Hummer, rå','Fisk og skaldyr',3.55),('Italiensk salat, mayonnaise','Krydderier/konserveringsmidler/ekstrakter',3.55),('Karrysalat, mayonnaise','Krydderier/konserveringsmidler/ekstrakter',3.55),('Mayonnaise','Krydderier/konserveringsmidler/ekstrakter',3.55),('Mandeldrik, ikke beriget','Mælk/æg/erstatningsprodukter',3.52),('Leverpostej','Kød/fjerkræ',3.51),('Kokosmælk','Frugt/grøntsagsprodukter',3.5),('Kylling, bryst, kød og skind, rå','Kød/fjerkræ',3.44),('Likør, kaffe med fløde','Drikkevarer',3.39),('Marinerede artiskokker','Frugt/grøntsagsprodukter',3.38),('Skinke, kogt, skiveskåret','Kød/fjerkræ',3.38),('Rullepølse, pålæg','Kød/fjerkræ',3.38),('Hamburgerryg, kogt','Kød/fjerkræ',3.38),('Kalkunkød, hakket, 5-10% fedt, råt','Kød/fjerkræ',3.34),('Pizza med tunfisk, dybfrost, færdigretter','Brød/bageartikler',3.26),('Kylling, bryst (filet), kogt, pålæg','Kød/fjerkræ',3.25),('Kyllingenuggets, friturestegt, færdigretter','Tilberedte/konserverede fødevarer',3.24),('Græskarkerner, tørret','Frugt/grøntsagsprodukter',3.2),('Kalkun, kød, rå','Kød/fjerkræ',3.18),('Kaffebønne, ristet, formalet','Drikkevarer',3.18),('Pizza med salami, tomat og ost, færdigretter','Brød/bageartikler',3.15),('Valnødder, tørret','Frugt/grøntsagsprodukter',3.15),('Oliven tapenade','Frugt/grøntsagsprodukter',3.12),('Vegansk mayo','Krydderier/konserveringsmidler/ekstrakter',3.07),('Kalkun, bryst (filet), kogt/røget, pålæg','Kød/fjerkræ',3.02),('Frikadeller','Kød/fjerkræ',3),('Pizza napolitana, dybfrost, færdigretter','Brød/bageartikler',2.99),('Småkage, hvede, traditionelle danske, industrifremstillet','Brød/bageartikler',2.98),('Grisekød, hakket, 15-20% fedt, råt','Kød/fjerkræ',2.97),('Pizza med tomat og ost, færdigretter','Brød/bageartikler',2.96),('Margarine, 70 %, bordbrug, blød, vegetabilsk fedt','Mælk/æg/erstatningsprodukter',2.93),('Plantemagarine','Spiseolie/-fedt',2.93),('Feta ost 40+','Mælk/æg/erstatningsprodukter',2.92),('Brie, ost 45+','Mælk/æg/erstatningsprodukter',2.92),('Hytteost, ost 20+','Mælk/æg/erstatningsprodukter',2.92),('Mozarella sticks','Tilberedte/konserverede fødevarer',2.92),('Blåskimmelost, ost','Mælk/æg/erstatningsprodukter',2.92),('Gedeost, ost','Mælk/æg/erstatningsprodukter',2.92),('Blåbær, rå','Frugt',2.91),('Grisekød, hakket, 5-10% fedt, råt','Kød/fjerkræ',2.9),('Lever, gris, i flødesovs, dybfrost, færdigretter','Kød/fjerkræ',2.9),('Mango chutney','Krydderier/konserveringsmidler/ekstrakter',2.9),('Marinerede grillede peberfrugter','Frugt/grøntsagsprodukter',2.89),('Müslibar','Brød/bageartikler',2.88),('Pizza romana, dybfrost, færdigretter','Brød/bageartikler',2.87),('Jordnøddesmør','Tilberedte/konserverede fødevarer',2.85),('Mælkeis, uspec.','Tilberedte/konserverede fødevarer',2.84),('Flødeis','Tilberedte/konserverede fødevarer',2.84),('Pommes frites, friturestegt, fastfood','Frugt/grøntsagsprodukter',2.84),('Sild, marineret','Fisk og skaldyr',2.83),('Hvidløg i olie','Frugt/grøntsagsprodukter',2.79),('Kiks, Marie','Brød/bageartikler',2.75),('Karameller, blandede','Slik/sukkervarer',2.74),('Kiks, fuldkorns-, digestivetype','Brød/bageartikler',2.73),('Småkage, cookie (amerikansk type), industrifremstillet','Brød/bageartikler',2.73),('Ørred, hav-, rå','Fisk og skaldyr',2.72),('Müslibar, chokolade','Korn-/gryn-/bælgfrugtprodukter',2.65),('Rosiner, uden kerner','Frugt/grøntsagsprodukter',2.63),('Kage, trøffelprodukt / koldprodukt, industrifremstillet','Brød/bageartikler',2.63),('Frugtsaft, blandet, sødet, koncentreret','Drikkevarer',2.58),('Pizza med broccoli, dybfrost, færdigretter','Brød/bageartikler',2.58),('Pizza med grønt og frugt, tomat og ost, færdigretter','Brød/bageartikler',2.56),('Sushi, færdigretter','Tilberedte/konserverede fødevarer',2.51),('Peanuts, jordnød, ristet og saltet','Frugt/grøntsagsprodukter',2.5),('Ajvar, relish','Krydderier/konserveringsmidler/ekstrakter',2.49),('Quinoa, sort, rå','Korn-/gryn-/bælgfrugtprodukter',2.49),('Tomatpure, koncentreret','Frugt/grøntsagsprodukter',2.48),('Hyldebærsaft, sød, koncentreret','Drikkevarer',2.4),('Vegansk pålæg','Frugt/grøntsagsprodukter',2.34),('Pizzasovs','Frugt/grøntsagsprodukter',2.33),('Pastasovs','Frugt/grøntsagsprodukter',2.33),('Thai meal panang curry chicken','Tilberedte/konserverede fødevarer',2.33),('And, kød, rå','Kød/fjerkræ',2.27),('Gås, kød, rå','Kød/fjerkræ',2.27),('Hare, rå','Kød/fjerkræ',2.27),('Kanin, kød, rå','Kød/fjerkræ',2.27),('Fasan, kød og skind, rå','Kød/fjerkræ',2.27),('Due, vildt, kød og skind, rå','Kød/fjerkræ',2.27),('Forårsruller, kylling, færdigretter','Tilberedte/konserverede fødevarer',2.24),('Kikærter, tørrede, rå','Korn-/gryn-/bælgfrugtprodukter',2.23),('Høne, kød og skind, rå','Kød/fjerkræ',2.22),('Kylling, hel','Kød/fjerkræ',2.22),('Pandekager, færdigretter','Tilberedte/konserverede fødevarer',2.21),('Medisterpølse, rå','Kød/fjerkræ',2.21),('Vegansk pulled beans','Tilberedte/konserverede fødevarer',2.21),('Sauce, barbeque','Krydderier/konserveringsmidler/ekstrakter',2.18),('Smoothie, jordbær blåbær','Drikkevarer',2.16),('Skærekage, chokoladekage/-pie','Brød/bageartikler',2.15),('Skærekage, formkage, uspec.','Brød/bageartikler',2.15),('Flødeskumskage, lagkagesnitte','Brød/bageartikler',2.15),('Fyldt frisk pasta','Tilberedte/konserverede fødevarer',2.14),('Fløde 38 %, piskefløde','Mælk/æg/erstatningsprodukter',2.14),('Morgenmadsprodukt, Guldkorn','Korn-/gryn-/bælgfrugtprodukter',2.12),('Vaffelrør','Brød/bageartikler',2.1),('Lakrids, salt','Slik/sukkervarer',2.09),('Kødrand, dybfrost, færdigretter','Kød/fjerkræ',2.08),('Veganske nuggets, sojabaseret','Tilberedte/konserverede fødevarer',2.05),('Bitter, Gammel Dansk Bitter Dram','Drikkevarer',2.04),('Vodka','Drikkevarer',2.04),('Snaps, 40 % vol., uspec.','Drikkevarer',2.04),('Veganske filetstykker','Tilberedte/konserverede fødevarer',2.04),('Sauce, gravad laks','Krydderier/konserveringsmidler/ekstrakter',2.03),('Vingummi','Slik/sukkervarer',2.02),('Creme fraiche 18 %','Mælk/æg/erstatningsprodukter',2.02),('Tomatketchup','Krydderier/konserveringsmidler/ekstrakter',2.01),('Sukker, stødt melis (saccharose)','Slik/sukkervarer',2),('Forårsrulle, dybfrost, færdigretter','Tilberedte/konserverede fødevarer',1.99),('Forårsrulle, friturestegt, færdigretter','Tilberedte/konserverede fødevarer',1.99),('Forårsruller, grøntsager, færdigretter','Tilberedte/konserverede fødevarer',1.99),('Grøn karrypasta','Krydderier/konserveringsmidler/ekstrakter',1.97),('Tomat, soltørret','Grøntsager',1.95),('Soltørrede tomater','Frugt/grøntsagsprodukter',1.95),('Boller i karry med ris og karrysauce, færdigretter','Tilberedte/konserverede fødevarer',1.94),('Eddike','Krydderier/konserveringsmidler/ekstrakter',1.93),('Sukker, brun farin','Slik/sukkervarer',1.92),('Tortilla chips, cheese','Tilberedte/konserverede fødevarer',1.9),('Remoulade, uspec.','Krydderier/konserveringsmidler/ekstrakter',1.88),('Chilli cheese tops','Tilberedte/konserverede fødevarer',1.87),('Hvidvin, uspec.','Drikkevarer',1.87),('Rosévin','Drikkevarer',1.87),('Rødvin, uspec.','Drikkevarer',1.87),('Hvidvin, mousserende, champagne','Drikkevarer',1.87),('Parisertoast, færdigretter','Tilberedte/konserverede fødevarer',1.87),('Sveske, rå','Frugt/grøntsagsprodukter',1.87),('Abrikos, tørret','Frugt/grøntsagsprodukter',1.87),('Cup noodles chicken','Tilberedte/konserverede fødevarer',1.87),('Ingefær, rod, rå','Grøntsager',1.85),('Daal, færdigretter','Tilberedte/konserverede fødevarer',1.85),('Vegansk chorizo','Tilberedte/konserverede fødevarer',1.84),('Tomatsuppe, spiseklar','Tilberedte/konserverede fødevarer',1.84),('Rispandekager','Korn-/gryn-/bælgfrugtprodukter',1.84),('Chilisauce','Krydderier/konserveringsmidler/ekstrakter',1.82),('Taco shells','Tilberedte/konserverede fødevarer',1.81),('Pølse, bratwurst, thüringer','Kød/fjerkræ',1.81),('Cocktailpølser','Kød/fjerkræ',1.81),('Grillpølser','Kød/fjerkræ',1.81),('Jordbærsyltetøj','Frugt/grøntsagsprodukter',1.8),('Hindbærmarmelade','Frugt/grøntsagsprodukter',1.8),('Veganske schnitzler, sojabaseret','Tilberedte/konserverede fødevarer',1.78),('Grønne linser, tørrede','Korn-/gryn-/bælgfrugtprodukter',1.78),('Røde linser, tørrede','Frugt/grøntsagsprodukter',1.78),('Musling, konserves','Fisk og skaldyr',1.77),('Solsikkefrø, afskallede, tørrede','Frugt/grøntsagsprodukter',1.77),('Wienerbrød, kanelsnegl','Brød/bageartikler',1.77),('Müsli, uspec.','Korn-/gryn-/bælgfrugtprodukter',1.76),('Sennep, gul, færdiglavet','Krydderier/konserveringsmidler/ekstrakter',1.76),('Lasagne, vegetarisk, færdigretter','Tilberedte/konserverede fødevarer',1.74),('Pasta, rå','Tilberedte/konserverede fødevarer',1.73),('Falafel, frost','Frugt/grøntsagsprodukter',1.72),('Vegansk bønnepostej','Frugt/grøntsagsprodukter',1.72),('Tofu, sojabønneost','Tilberedte/konserverede fødevarer',1.71),('Kastanje, ægte, rå','Frugt/grøntsagsprodukter',1.71),('Linsespirer, rå','Grøntsager',1.7),('Halve ferskner i juice','Frugt/grøntsagsprodukter',1.7),('Morgenmadsprodukt, Havrefras','Korn-/gryn-/bælgfrugtprodukter',1.7),('Oliven, grønne, marinerede, konserves','Krydderier/konserveringsmidler/ekstrakter',1.69),('Oliven, sorte, uden sten, i saltlage','Krydderier/konserveringsmidler/ekstrakter',1.69),('Falafel-fars','Tilberedte/konserverede fødevarer',1.68),('Æblemost, uspec.','Drikkevarer',1.64),('Æblejuice','Drikkevarer',1.64),('Veganske pølser, sojabaseret','Tilberedte/konserverede fødevarer',1.63),('Mikroovns popcorn','Frugt/grøntsagsprodukter',1.62),('Lasagne, vegansk, færdigretter','Tilberedte/konserverede fødevarer',1.59),('Salsa, hot','Frugt/grøntsagsprodukter',1.59),('Agurk, syltet','Frugt/grøntsagsprodukter',1.58),('Asier, syltede','Frugt/grøntsagsprodukter',1.58),('Cornichoner','Frugt/grøntsagsprodukter',1.58),('Rugbrød, revet, med brunt sukker, uspec.','Brød/bageartikler',1.56),('Peber, chili, konserves','Frugt/grøntsagsprodukter',1.54),('Jalapenos','Frugt/grøntsagsprodukter',1.54),('Vegansk is, havre','Tilberedte/konserverede fødevarer',1.53),('Baked beans/hvide bønner i tomatsovs, konserves','Frugt/grøntsagsprodukter',1.53),('Cornflakes, uspec.','Korn-/gryn-/bælgfrugtprodukter',1.51),('Æggesalat','Tilberedte/konserverede fødevarer',1.51),('Hummus, færdig','Krydderier/konserveringsmidler/ekstrakter',1.5),('Soja sauce','Krydderier/konserveringsmidler/ekstrakter',1.49),('Svampeburger','Tilberedte/konserverede fødevarer',1.47),('Aspargessnitter','Frugt/grøntsagsprodukter',1.46),('Porretærte med bacon, færdigretter','Tilberedte/konserverede fødevarer',1.46),('Bagegær, presset, rå','Brød/bageartikler',1.45),('Kyllingepølse, pålæg','Kød/fjerkræ',1.44),('Haricots verts, frost','Grøntsager',1.42),('Risnudler','Tilberedte/konserverede fødevarer',1.41),('Kylling, ben, kød og skind, rå','Kød/fjerkræ',1.39),('Kapers','Frugt/grøntsagsprodukter',1.39),('Grønne ærter, konserves','Frugt/grøntsagsprodukter',1.36),('Hvidløg, rå','Grøntsager',1.33),('Kylling, lår, kød og skind, rå','Kød/fjerkræ',1.32),('Appelsinjuice, konserves','Drikkevarer',1.32),('Æbleskiver','Brød/bageartikler',1.32),('Melboller, dybfrost','Tilberedte/konserverede fødevarer',1.31),('Kartoffelmel','Frugt/grøntsagsprodukter',1.31),('Ananas, konserves','Frugt/grøntsagsprodukter',1.3),('Ris, parboiled, rå','Korn-/gryn-/bælgfrugtprodukter',1.28),('Melboller, uspec.','Tilberedte/konserverede fødevarer',1.27),('Tomatjuice, konserves','Drikkevarer',1.26),('Tomat, flået, konserves','Frugt/grøntsagsprodukter',1.26),('Rasp','Brød/bageartikler',1.26),('Skyr, 0.2 % fedt','Mælk/æg/erstatningsprodukter',1.25),('Grøntsagsbøffer','Frugt/grøntsagsprodukter',1.25),('Pop corn, traditionelle','Tilberedte/konserverede fødevarer',1.25),('Mayonnaise, fedtreduceret','Krydderier/konserveringsmidler/ekstrakter',1.22),('Bambusskud, konserves, usaltet','Frugt/grøntsagsprodukter',1.21),('Kartoffelsalat','Frugt/grøntsagsprodukter',1.18),('Hvidløgsbaguette, frossen','Tilberedte/konserverede fødevarer',1.18),('Samosa, vegetarisk frost','Tilberedte/konserverede fødevarer',1.17),('Sojabønner, tørrede, rå','Korn-/gryn-/bælgfrugtprodukter',1.16),('Ispind, limonade','Tilberedte/konserverede fødevarer',1.15),('Nudler, æg','Tilberedte/konserverede fødevarer',1.15),('Bulgur, parboiled, rå','Korn-/gryn-/bælgfrugtprodukter',1.15),('Madkorn, kamut','Korn-/gryn-/bælgfrugtprodukter',1.15),('Rismel','Korn-/gryn-/bælgfrugtprodukter',1.14),('Østers, rå','Fisk og skaldyr',1.13),('Risengryn, grødris, polerede, rå','Korn-/gryn-/bælgfrugtprodukter',1.11),('Kartoffelgratin / flødekartofler','Frugt/grøntsagsprodukter',1.1),('Cider  4,5 %','Drikkevarer',1.1),('Yoghurt naturel, sødmælk','Mælk/æg/erstatningsprodukter',1.08),('Knækbrød, rug-, groft','Brød/bageartikler',1.08),('Frisk pasta, fettucine spinat','Tilberedte/konserverede fødevarer',1.08),('Avocado, rå','Frugt',1.07),('Kidney bønner','Frugt/grøntsagsprodukter',1.05),('Sorte bønner','Frugt/grøntsagsprodukter',1.05),('Abrikos, rå','Frugt',1.05),('Voksbønner, rå','Grøntsager',1.04),('Grønne bønner, rå','Grøntsager',1.04),('Bønnespirer, mung, rå','Frugt/grøntsagsprodukter',1.04),('Bønnespirer, uspec., rå','Frugt/grøntsagsprodukter',1.04),('Halve pærer i juice','Frugt/grøntsagsprodukter',1.04),('Blåbær, frosne, usukrede','Frugt/grøntsagsprodukter',1.02),('Peberfrugt, rød, rå','Grøntsager',1.02),('Peber, chili, rå','Grøntsager',1.02),('Vegansk ost, revet eller skiver','Mælk/æg/erstatningsprodukter',1),('Jordbær, dybfrost, usukrede','Frugt/grøntsagsprodukter',0.98),('Banan, rå','Frugt',0.98),('Artiskok, rå','Grøntsager',0.98),('Aubergine, rå','Grøntsager',0.97),('Mandarin, rå','Frugt',0.97),('Vegansk blok','Mælk/æg/erstatningsprodukter',0.97),('Koldskål','Mælk/æg/erstatningsprodukter',0.97),('Fersken, rå','Frugt',0.96),('Nektarin, rå','Frugt',0.96),('Havregryn, ikke beriget','Korn-/gryn-/bælgfrugtprodukter',0.95),('Citron, rå','Frugt',0.94),('Vindrue, rå','Frugt/grøntsagsprodukter',0.94),('Mango, rå','Frugt',0.93),('Sirup','Slik/sukkervarer',0.93),('Kiwi, rå','Frugt',0.93),('Brombær, rå','Frugt',0.91),('Hyldebær, rå','Frugt',0.91),('Løg, rå','Grøntsager',0.9),('Forårsløg, rå','Grøntsager',0.9),('Rødløg','Grøntsager',0.9),('Persillerod, rå','Grøntsager',0.89),('Kartoffel, konserves','Frugt/grøntsagsprodukter',0.89),('Asparges, grønne, rå','Grøntsager',0.88),('Kikærter, dåse','Frugt/grøntsagsprodukter',0.88),('Grønne ærter, dybfrost','Grøntsager',0.87),('Lime, rå','Frugt',0.87),('Tranebær, rå','Frugt',0.87),('Vegansk boller, sojabaseret','Tilberedte/konserverede fødevarer',0.86),('Grapefrugt, rå','Frugt',0.86),('Hindbær, dybfrost, rå','Frugt/grøntsagsprodukter',0.86),('Vegansk burgere, sojabaseret','Tilberedte/konserverede fødevarer',0.85),('Stikkelsbær, rå','Frugt',0.85),('Æg, høne, skrabehøns, rå','Mælk/æg/erstatningsprodukter',0.85),('Blomkål, dybfrost','Frugt/grøntsagsprodukter',0.84),('Broccoli, dybfrost','Frugt/grøntsagsprodukter',0.84),('Hindbær, rå','Frugt',0.84),('Hvedemel, grahemsmel, fuldkorn','Korn-/gryn-/bælgfrugtprodukter',0.84),('Hvedekerner, hele/knækkede','Korn-/gryn-/bælgfrugtprodukter',0.84),('Squash, rå','Grøntsager',0.83),('Græskar, rå','Grøntsager',0.83),('Byggryn, rå','Korn-/gryn-/bælgfrugtprodukter',0.83),('Rødbede, konserves','Frugt/grøntsagsprodukter',0.83),('Pære, konserves','Frugt/grøntsagsprodukter',0.83),('Icetea, fersken','Drikkevarer',0.82),('Appelsin, rå','Frugt',0.81),('Sødmælk, 3,5 % fedt','Mælk/æg/erstatningsprodukter',0.79),('Plantepostej, ærteprotein og svamp','Frugt/grøntsagsprodukter',0.78),('Vandmelon, rå','Frugt',0.78),('Honning','Tilberedte/konserverede fødevarer',0.77),('Rugmel, groft, fuldkorn','Korn-/gryn-/bælgfrugtprodukter',0.77),('Rugkerner, hele/knækkede','Korn-/gryn-/bælgfrugtprodukter',0.77),('Kirsebær, rå','Frugt',0.75),('Maniok, kassava, rå','Grøntsager',0.74),('Sukkerært (mangetout), rå','Grøntsager',0.74),('Hvedebrød, toastbrød, fint, industrifremstillet','Brød/bageartikler',0.74),('Pølsebrød','Brød/bageartikler',0.74),('Grovbolle','Brød/bageartikler',0.74),('Tortillabrød, hvede','Brød/bageartikler',0.74),('Burgerboller','Brød/bageartikler',0.74),('Rødkål, konserves, uden tilsat sukker','Frugt/grøntsagsprodukter',0.73),('Pizzadej','Brød/bageartikler',0.72),('Alko-sodavand, 4 %','Drikkevarer',0.72),('Blomkål, uspecificeret, rå','Grøntsager',0.71),('Broccoli, rå','Grøntsager',0.71),('Honningmelon, rå','Frugt',0.71),('Champignon, konserves','Frugt/grøntsagsprodukter',0.7),('Tomat, uspec., rå','Grøntsager',0.7),('Grønne ærter, rå','Grøntsager',0.67),('Æble, uspec., råt','Frugt/grøntsagsprodukter',0.66),('Cacaoskummetmælk (UHT)','Mælk/æg/erstatningsprodukter',0.65),('Pommes frites, frost','Frugt/grøntsagsprodukter',0.65),('Ananas, rå','Frugt',0.65),('Hyben, rå','Frugt',0.65),('Spinat, hakket, dybfrost','Frugt/grøntsagsprodukter',0.64),('Majsmel','Korn-/gryn-/bælgfrugtprodukter',0.63),('Rugbrød, fuldkorn, industrifremstillet','Brød/bageartikler',0.62),('Majs, kerner, konserves','Frugt/grøntsagsprodukter',0.61),('Babymajs','Frugt/grøntsagsprodukter',0.61),('Vegansk fars, plantefars, ærtebaseret','Tilberedte/konserverede fødevarer',0.61),('Letmælk, 1,5 % fedt','Mælk/æg/erstatningsprodukter',0.61),('Øl, pilsner, 4.4 % vol.','Drikkevarer',0.6),('Øl, hvidtøl, letøl','Drikkevarer',0.6),('Øl, stærk, 7,6 % vol.','Drikkevarer',0.6),('Salat, Iceberg, rå','Grøntsager',0.59),('Salat, egeløv, rå','Grøntsager',0.59),('Blomme, rå','Frugt',0.58),('Jordbær, rå','Frugt',0.56),('Agurk, rå','Grøntsager',0.56),('Drueagurk (sylteagurk), rå','Grøntsager',0.56),('Mineralvand, sodavand, tilsat sukker, uspec.','Drikkevarer',0.55),('Solbær, rå','Frugt',0.53),('Ribs, rå','Frugt',0.53),('Energidrik','Drikkevarer',0.53),('Spinat, rå','Grøntsager',0.52),('Bouillon, oksekød, spiseklar','Krydderier/konserveringsmidler/ekstrakter',0.52),('Majs, hele kolber','Grøntsager',0.49),('Ærtedrik','Mælk/æg/erstatningsprodukter',0.48),('Rucola salat, skyllet','Tilberedte/konserverede fødevarer',0.48),('Minimælk, 0,5 % fedt','Mælk/æg/erstatningsprodukter',0.48),('Champignon, rå','Grøntsager',0.47),('Østershatte','Grøntsager',0.47),('Blæksprutte, rå','Fisk og skaldyr',0.46),('Salt, bordsalt (jodberiget)','Krydderier/konserveringsmidler/ekstrakter',0.44),('Skivet champignon','Frugt/grøntsagsprodukter',0.41),('Risdrik, tilsat calcium','Mælk/æg/erstatningsprodukter',0.39),('Pære, rå','Frugt/grøntsagsprodukter',0.38),('Sojadrik, tilsat calcium','Mælk/æg/erstatningsprodukter',0.38),('Bouillon, hønsekød, spiseklar','Krydderier/konserveringsmidler/ekstrakter',0.38),('Havredrik, tilsat calcium','Mælk/æg/erstatningsprodukter',0.37),('Gulerod, dybfrost','Frugt/grøntsagsprodukter',0.37),('Persille, rå','Grøntsager',0.37),('Dild, rå','Grøntsager',0.37),('Karse, frisk','Grøntsager',0.37),('Radise, rå','Grøntsager',0.37),('Løg, hakkede, dybfrost','Frugt/grøntsagsprodukter',0.37),('Portobello-svamp','Grøntsager',0.36),('Kartoffel, uspec., rå','Grøntsager',0.36),('Purløg, rå','Grøntsager',0.32),('Porre, rå','Grøntsager',0.32),('Rødbede, rå','Grøntsager',0.31),('Rabarber, rå','Grøntsager',0.3),('Bladselleri, rå','Grøntsager',0.3),('Selleri, rod, rå','Grøntsager',0.3),('Basilikum, frisk','Grøntsager',0.3),('Gulerod, uspec., rå','Grøntsager',0.25),('Majroe, rå','Grøntsager',0.25),('Vegansk bacon','Tilberedte/konserverede fødevarer',0.25),('Grønkål, rå','Grøntsager',0.25),('Hvidkål, rå','Grøntsager',0.25),('Rosenkål, uspec., rå','Grøntsager',0.25),('Rødkål, rå','Grøntsager',0.25),('Spidskål, rå','Grøntsager',0.25),('Paksoi, pak-choi, pai tsai, rå','Grøntsager',0.25),('Savoykål, rå','Grøntsager',0.25),('Musling, rå','Fisk og skaldyr',0.22),('Mineralvand, dansk vand o.lign.','Drikkevarer',0.22),('Vand, postevand, vejl. Værdier','Drikkevarer',0);
/*!40000 ALTER TABLE `CONCITO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-30 10:08:09
