CREATE TABLE `NewTable` (
`id`  int(20) NOT NULL AUTO_INCREMENT ,
`bar_code`  varchar(50) NULL DEFAULT '' COMMENT '物流单号' ,
`weight`  double(10,2) NULL DEFAULT 0.00 COMMENT '包裹重量' ,
PRIMARY KEY (`id`)
)
;

