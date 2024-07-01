package top.ytazwc.poi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yt
 * 2024-07-01
 * 订货
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoods {

	/**
	 * 订货时间
	 */
	private LocalDateTime time;

	/**
	 * 订货单位
	 */
	private String orderUnit;

	/**
	 * 供货商
	 */
	private String supplier;

	/**
	 * 订货人
	 */
	private String orderer;

	/**
	 * 复核人
	 */
	private String reviewer;

	/**
	 * 订购货品数
	 */
	private Integer numGoods;

	/**
	 * 申购数量
	 */
	private Double purchaseQuantity;

	/**
	 * 类型 职工  营养
	 */
	private String type;

	/**
	 * 详情表头行
	 */
	private Integer headerRowIndex;

	/**
	 * 详情数据开始行
	 */
	private Integer startRowIndex;

	/**
	 * 详情数据结束行
	 */
	private Integer endRowIndex;

	private List<OrderGoodsDetail> detailList;

}
