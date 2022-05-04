/*
 * SearchConditionHundler.java
 *
 * Created on 2002/04/08, 0:09
 */

package jp.co.lastminute.common.searchCondition;

import java.io.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class SearchConditionHundler {
    private Cultures culture;
    private DomesticHotel domestichotel;
    private DomesticTours domestictours;
    private Entertaiment entertaiment;
    private Gift gift;
    private OverseaFlight overseaflight;
    private OverseaHotel overseahotel;
    private OverseaTours overseatours;
    private Selection selection;
    private Topdestination topdestination;
    /** Creates new SearchConditionHundler */
    public SearchConditionHundler() {
    }

    /** プロパティ culture の取得メソッド。
     * @return プロパティ culture の値。
     */
    public Cultures getCulture() {
        return culture;
    }
    
    /** プロパティ culture の設定メソッド。
     * @param culture プロパティ culture の新しい値。
     */
    public void setCulture(Cultures culture) {
        this.culture = culture;
    }
    
    /** プロパティ domestichotel の取得メソッド。
     * @return プロパティ domestichotel の値。
     */
    public DomesticHotel getDomestichotel() {
        return domestichotel;
    }
    
    /** プロパティ domestichotel の設定メソッド。
     * @param domestichotel プロパティ domestichotel の新しい値。
     */
    public void setDomestichotel(DomesticHotel domestichotel) {
        this.domestichotel = domestichotel;
    }
    
    /** プロパティ domestictours の取得メソッド。
     * @return プロパティ domestictours の値。
     */
    public DomesticTours getDomestictours() {
        return domestictours;
    }
    
    /** プロパティ domestictours の設定メソッド。
     * @param domestictours プロパティ domestictours の新しい値。
     */
    public void setDomestictours(DomesticTours domestictours) {
        this.domestictours = domestictours;
    }
    
    /** プロパティ entertaiment の取得メソッド。
     * @return プロパティ entertaiment の値。
     */
    public Entertaiment getEntertaiment() {
        return entertaiment;
    }
    
    /** プロパティ entertaiment の設定メソッド。
     * @param entertaiment プロパティ entertaiment の新しい値。
     */
    public void setEntertaiment(Entertaiment entertaiment) {
        this.entertaiment = entertaiment;
    }
    
    /** プロパティ gift の取得メソッド。
     * @return プロパティ gift の値。
     */
    public Gift getGift() {
        return gift;
    }
    
    /** プロパティ gift の設定メソッド。
     * @param gift プロパティ gift の新しい値。
     */
    public void setGift(Gift gift) {
        this.gift = gift;
    }
    
    /** プロパティ overseaflight の取得メソッド。
     * @return プロパティ overseaflight の値。
     */
    public OverseaFlight getOverseaflight() {
        return overseaflight;
    }
    
    /** プロパティ overseaflight の設定メソッド。
     * @param overseaflight プロパティ overseaflight の新しい値。
     */
    public void setOverseaflight(OverseaFlight overseaflight) {
        this.overseaflight = overseaflight;
    }
    
    /** プロパティ overseatours の取得メソッド。
     * @return プロパティ overseatours の値。
     */
    public OverseaTours getOverseatours() {
        return overseatours;
    }
    
    /** プロパティ overseatours の設定メソッド。
     * @param overseatours プロパティ overseatours の新しい値。
     */
    public void setOverseatours(OverseaTours overseatours) {
        this.overseatours = overseatours;
    }
    
    /** プロパティ selection の取得メソッド。
     * @return プロパティ selection の値。
     */
    public Selection getSelection() {
        return selection;
    }
    
    /** プロパティ selection の設定メソッド。
     * @param selection プロパティ selection の新しい値。
     */
    public void setSelection(Selection selection) {
        this.selection = selection;
    }
    
    /** プロパティ topdestination の取得メソッド。
     * @return プロパティ topdestination の値。
     */
    public Topdestination getTopdestination() {
        return topdestination;
    }
    
    /** プロパティ topdestination の設定メソッド。
     * @param topdestination プロパティ topdestination の新しい値。
     */
    public void setTopdestination(Topdestination topdestination) {
        this.topdestination = topdestination;
    }
    
}
