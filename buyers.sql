/*
 Navicat Premium Data Transfer


 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 127.0.0.1:3306
 Source Schema         : buyers

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 17/12/2019 16:10:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comments_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comments_centent` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '评论的内容',
  `comments_user_id` int(255) DEFAULT NULL COMMENT '评论的用户id',
  `comments_produce_id` int(255) DEFAULT NULL COMMENT '评论的商品id',
  `comments_stars` int(11) DEFAULT NULL COMMENT '评论的星级',
  `comments_date` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '评论的时间',
  PRIMARY KEY (`comments_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (4, 79, '芙丽芳丝净润', 16, '2019-12-15 04:24:21');
INSERT INTO `history` VALUES (5, 145, 'CNKR成牛 ', 16, '2019-12-15 04:24:39');
INSERT INTO `history` VALUES (6, 79, 'DiaForce黑珍珠', 16, '2019-12-15 04:24:53');
INSERT INTO `history` VALUES (7, 79, '芙丽芳丝净润', 16, '2019-12-15 05:34:44');
INSERT INTO `history` VALUES (8, 79, '芙丽芳丝净润', 16, '2019-12-15 05:49:15');
INSERT INTO `history` VALUES (9, 145, 'CNKR成牛 ', 16, '2019-12-15 05:49:20');
INSERT INTO `history` VALUES (10, 79, 'DiaForce黑珍珠', 16, '2019-12-15 05:49:24');
INSERT INTO `history` VALUES (11, 79, '芙丽芳丝净润', 17, '2019-12-15 07:38:15');
INSERT INTO `history` VALUES (12, 79, '芙丽芳丝净润', 17, '2019-12-15 07:38:56');
INSERT INTO `history` VALUES (13, 111, 'test', 17, '2019-12-15 08:02:39');
INSERT INTO `history` VALUES (14, 110, '限量菁纯宝石唇膏', 22, '2019-12-16 08:01:02');
INSERT INTO `history` VALUES (15, 79, 'DiaForce黑珍珠', 21, '2019-12-16 09:43:28');
INSERT INTO `history` VALUES (16, 145, 'CNKR成牛 ', 23, '2019-12-16 10:44:33');
INSERT INTO `history` VALUES (17, 110, '限量菁纯宝石唇膏', 22, '2019-12-16 10:51:05');
INSERT INTO `history` VALUES (18, 145, 'CNKR成牛 ', 22, '2019-12-16 10:51:17');
INSERT INTO `history` VALUES (19, 79, 'DiaForce黑珍珠', 23, '2019-12-16 10:59:25');
INSERT INTO `history` VALUES (20, 110, '限量菁纯宝石唇膏', 23, '2019-12-16 11:05:07');
INSERT INTO `history` VALUES (21, 79, 'DiaForce黑珍珠', 23, '2019-12-16 11:10:45');
INSERT INTO `history` VALUES (22, 145, 'CNKR成牛 ', 22, '2019-12-16 11:19:24');
INSERT INTO `history` VALUES (23, 79, 'DiaForce黑珍珠', 23, '2019-12-16 11:20:56');
INSERT INTO `history` VALUES (24, 79, 'DiaForce黑珍珠', 23, '2019-12-16 12:00:46');
INSERT INTO `history` VALUES (25, 145, 'CNKR成牛 ', 23, '2019-12-16 12:01:46');
INSERT INTO `history` VALUES (26, 145, 'CNKR成牛 ', 23, '2019-12-16 12:26:08');
INSERT INTO `history` VALUES (27, 79, 'DiaForce黑珍珠', 23, '2019-12-16 12:26:12');
INSERT INTO `history` VALUES (28, 249, '韩束护肤品化妆品套装女面膜巨补水保湿提亮肤色 墨菊补水润养礼盒八件套(洗面', 23, '2019-12-16 12:28:53');
INSERT INTO `history` VALUES (29, 249, '韩束护肤品化妆品套', 22, '2019-12-16 12:32:55');
INSERT INTO `history` VALUES (30, 249, '韩束护肤品化妆品套', 24, '2019-12-16 12:36:24');
INSERT INTO `history` VALUES (31, 249, '韩束护肤品化妆品套', 26, '2019-12-16 12:58:58');
INSERT INTO `history` VALUES (32, 249, '韩束护肤品化妆品套', 26, '2019-12-16 12:59:06');
INSERT INTO `history` VALUES (33, 145, 'CNKR成牛 ', 26, '2019-12-16 13:01:28');
INSERT INTO `history` VALUES (34, 249, '韩束护肤品化妆品套', 26, '2019-12-16 13:01:33');

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `produce_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品id',
  `pay_state` int(11) DEFAULT 0 COMMENT '支付状态（1已支付，0未支付）',
  `order_num` int(11) DEFAULT NULL COMMENT '订单号',
  `pay_time` datetime(0) DEFAULT NULL COMMENT '支付时间',
  `total_price` double(10, 0) DEFAULT NULL COMMENT '订单总价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES (5, 16, ',10,11', 2, 2, '2019-12-15 03:14:51', 224);
INSERT INTO `order_details` VALUES (6, 16, ',11', 2, 1, '2019-12-15 04:24:44', 145);
INSERT INTO `order_details` VALUES (7, 16, ',12', 2, 1, '2019-12-15 04:24:58', 79);
INSERT INTO `order_details` VALUES (8, 16, ',10', 2, 1, '2019-12-15 05:34:49', 79);
INSERT INTO `order_details` VALUES (9, 17, ',10', 2, 1, '2019-12-15 07:38:19', 79);
INSERT INTO `order_details` VALUES (10, 22, ',14', 2, 1, '2019-12-16 08:01:12', 110);
INSERT INTO `order_details` VALUES (11, 21, ',12', 2, 1, '2019-12-16 09:43:33', 79);
INSERT INTO `order_details` VALUES (12, 23, ',11', 2, 1, '2019-12-16 10:44:39', 145);
INSERT INTO `order_details` VALUES (13, 22, ',14', 2, 1, '2019-12-16 10:51:10', 110);
INSERT INTO `order_details` VALUES (14, 23, ',12', 2, 1, '2019-12-16 10:59:30', 79);
INSERT INTO `order_details` VALUES (15, 23, ',14', 2, 1, '2019-12-16 11:05:52', 110);
INSERT INTO `order_details` VALUES (16, 23, ',12', 2, 1, '2019-12-16 11:10:48', 79);
INSERT INTO `order_details` VALUES (17, 22, ',11', 2, 1, '2019-12-16 11:19:27', 145);
INSERT INTO `order_details` VALUES (18, 23, ',12', 2, 1, '2019-12-16 11:21:00', 79);
INSERT INTO `order_details` VALUES (19, 23, ',12', 2, 1, '2019-12-16 12:00:50', 79);
INSERT INTO `order_details` VALUES (20, 23, '12', 2, 1, '2019-12-16 12:26:17', 79);
INSERT INTO `order_details` VALUES (21, 23, '11', 2, 2, '2019-12-16 12:26:19', 290);
INSERT INTO `order_details` VALUES (22, 22, '15', 2, 1, '2019-12-16 12:33:00', 249);
INSERT INTO `order_details` VALUES (23, 24, '15', 2, 2, '2019-12-16 12:36:30', 498);
INSERT INTO `order_details` VALUES (24, 26, '15', 1, 2, '2019-12-16 12:59:12', 498);

-- ----------------------------
-- Table structure for produce_sell
-- ----------------------------
DROP TABLE IF EXISTS `produce_sell`;
CREATE TABLE `produce_sell`  (
  `produce_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品详情id',
  `produce_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名字',
  `produce_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品说明',
  `produce_produce_sortnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品种类编号',
  `produce_count` int(11) DEFAULT NULL COMMENT '商品库存数量',
  `produce_price` decimal(10, 2) DEFAULT NULL COMMENT '商品实际销售价格',
  `produce_shop_price` decimal(10, 2) DEFAULT NULL COMMENT '商品原销售价格',
  `produce_imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品图片地址',
  `produce_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品的作者',
  `produce_sale_count` int(11) DEFAULT NULL COMMENT '已销售数量',
  `produce_hot` int(11) DEFAULT NULL COMMENT '商品热度',
  `produce_creat_user_id` int(11) DEFAULT 2 COMMENT '发布商品的用户id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`produce_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of produce_sell
-- ----------------------------
INSERT INTO `produce_sell` VALUES (11, 'CNKR成牛 ', 'CNKR成牛 ABOUT ME奥本美 ', 'wx001', 99, 145.00, 145.00, 'https://springbootmall.oss-cn-beijing.aliyuncs.com/image/962c5479-0232-4de3-92c4-fbdedc594a98TB1NZBVJFXXXXaMXFXX9OdpFFXX_160x160.png', '未知', NULL, NULL, 2, NULL, NULL);
INSERT INTO `produce_sell` VALUES (12, 'DiaForce黑珍珠', 'DiaForce黑珍珠', 'wx001', 100, 79.00, 79.00, 'https://springbootmall.oss-cn-beijing.aliyuncs.com/image/4e901208-ca3c-4869-a966-24626352a514TB1x70RIXXXXXcJXXXXklIkFFXX_094827.jpg_160x160.jpg', '未知', NULL, NULL, 2, NULL, NULL);
INSERT INTO `produce_sell` VALUES (14, '限量菁纯宝石唇膏', '用过的都说好用', 'wx001', 100, 110.00, 110.00, 'https://springbootmall.oss-cn-beijing.aliyuncs.com/image/28492f21-85dc-4b7b-b97e-e2e93e82ed57[F4UV~J@JJZJIUZSEPBKLJJ.png', '未知', NULL, NULL, 2, NULL, NULL);
INSERT INTO `produce_sell` VALUES (15, '韩束护肤品化妆品套', '韩束护肤品化妆品', 'wx001', 98, 249.00, 249.00, 'https://springbootmall.oss-cn-beijing.aliyuncs.com/image/956adf6e-1b63-4b41-8f5d-034c678f34b1cf40b80885ef5f08.jpg', '未知', NULL, NULL, 2, NULL, NULL);

-- ----------------------------
-- Table structure for produce_sell_category
-- ----------------------------
DROP TABLE IF EXISTS `produce_sell_category`;
CREATE TABLE `produce_sell_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类目id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品类别名称',
  `
describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品类别描述',
  `sortnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品种类编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sortnum`(`sortnum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of produce_sell_category
-- ----------------------------
INSERT INTO `produce_sell_category` VALUES (4, '美妆', '用形象反映社会生活', 'wx001');

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `produce_id` int(11) DEFAULT NULL COMMENT '商品id',
  `cart_produce_count` int(11) DEFAULT NULL COMMENT '购物车中此商品的数量',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (26, 16, 10, 1, '2019-12-15 13:49:16', '2019-12-15 13:49:16');
INSERT INTO `shopping_cart` VALUES (27, 16, 11, 1, '2019-12-15 13:49:20', '2019-12-15 13:49:20');
INSERT INTO `shopping_cart` VALUES (28, 16, 12, 1, '2019-12-15 13:49:24', '2019-12-15 13:49:24');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `user_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户性别',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
  `user_
address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户地址',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户电话',
  `user_headimgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像图片地址',
  `user_status` int(255) DEFAULT NULL COMMENT '状态（1禁用，0未禁用）',
  `user_type` int(255) DEFAULT NULL COMMENT '身份标识（1买家，2卖家，3超级管理员）',
  `user_money` decimal(10, 1) DEFAULT NULL COMMENT '用户账户余额',
  `user_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_uname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (16, '15147418599', NULL, '15147418599', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (17, '123456', NULL, '123456', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (18, '1234567', NULL, '1234567', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (19, '789456', '男', '123456', 'test', '15147852588', NULL, NULL, NULL, NULL, NULL, '管理员');
INSERT INTO `user` VALUES (20, '123456789', '女', '123456', 'testdsfasdf', '15147859855', NULL, NULL, NULL, NULL, '1715585756@qq.com', 'test');
INSERT INTO `user` VALUES (21, 'test', '女', '123456', 'test', '15147859588', NULL, NULL, NULL, NULL, '15147859588@qq.com', 'test');
INSERT INTO `user` VALUES (22, '460021749@qq.com', '男', '123456', '123456', '18925128823', NULL, NULL, NULL, NULL, '460021749@qq.com', '陈明睿');
INSERT INTO `user` VALUES (23, 'wdd', '女', '123', 'test', '15147852599', NULL, NULL, NULL, NULL, '1710031565@qq.com', '张三丰');
INSERT INTO `user` VALUES (24, '648870603@qq.com', '男', '123456', 'asdfaf', '12345678910', NULL, NULL, NULL, NULL, '648870603@qq.com', 'ssss');
INSERT INTO `user` VALUES (25, 'hhhhh', '男', '123456', '11111', '11111111111', NULL, NULL, NULL, NULL, '1111', '1111111');
INSERT INTO `user` VALUES (26, 'asd', '男', '123456', '111', '12345678911', NULL, NULL, NULL, NULL, '111', '1111');

SET FOREIGN_KEY_CHECKS = 1;
