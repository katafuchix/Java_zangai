package jp.co.lastminute.maintenance.xmlstruct;

import java.io.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Production implements Serializable {
	public Plan plan = null;
	public Product product = null;
	
	/**
	 * コンストラクタ
	 */
	public Production(){
	}
	public Production( Plan plan, Product product){
		this.plan = plan;
		this.product = product;
	}
	/**
	 * XMLジェネレート
	 */
	synchronized public String generateXml() throws Exception{
		return "<production>\n" + this.plan.generateXml() + "\n"
				+ this.product.generateXml() + "\n</production>";
	}
	/**
	 * Returns the plan.
	 * @return Plan
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * Returns the product.
	 * @return Product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the plan.
	 * @param plan The plan to set
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	/**
	 * Sets the product.
	 * @param product The product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

}
