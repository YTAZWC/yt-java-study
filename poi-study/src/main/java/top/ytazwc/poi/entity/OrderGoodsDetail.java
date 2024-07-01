package top.ytazwc.poi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yt
 * 2024-07-01
 * 订货
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoodsDetail {

	/**
	 * 订货主表id
	 */
	private String orderGoodsId;

	/**
	 * 生产商
	 */
	private String producer;

	/**
	 * 质保期
	 */
	private String warrantyPeriod;

	/**
	 * 货品名称
	 */

	private String name;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 规格
	 */
	private String spec;

	/**
	 * 货品单位
	 */
	private String unit;

	/**
	 * 产地
	 */
	private String placeOrigin;

	/**
	 * 申购数量

	 */
	private Double purchaseQuantity;

	/**
	 * 备注
	 */
	private String remark;
}
