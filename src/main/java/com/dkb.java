//package gzict.hpg.components.DKButils;
//
//
//import com.zbiti.core.components.db.DBQuery;
//import com.zbiti.core.util.db.DBConnection;
//import gzict.pub.DBProc;
//import gzict.pub.IctPmReport;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author cuikai
// * @date 2023/9/22 11:08:24
// */
//public class DKButils extends IctPmReport {
//    DBQuery dbq = new DBQuery(this.dbc);
//    DBProc dbp = new DBProc(this.dbc);
//    IctPmReport ictPmReport = new IctPmReport(dbc);
//    public DKButils(DBConnection dbc) {
//        super(dbc);
//    }
//    public  HashMap<String,String> iteminfo(HashMap<String,String> inputMap,Map<String, String> map) throws SQLException {
//        HashMap<String, String> iteminfo_data = new HashMap<>();
//        iteminfo_data.put("PERIOD",map.get("PERIOD"));
//        iteminfo_data.put("LRZXZ_NAME",map.get("LRZXZ_NAME"));
//        iteminfo_data.put("LRZX_NAME",map.get("LRZX_NAME"));
//        iteminfo_data.put("PP_STATUS",map.get("STATUS_NAME"));
//        iteminfo_data.put("PP_MANGER",map.get("PP_MANGER"));
//        iteminfo_data.put("CUST_MANGER",map.get("CUST_MANGER"));
//        iteminfo_data.put("PP_TYPE",map.get("DFNAME"));
//
//        iteminfo_data.put("LRZXZ_CODE",inputMap.get("PROFITCENTER").substring(0,5));
//        iteminfo_data.put("LRZX_CODE",inputMap.get("PROFITCENTER"));
//        iteminfo_data.put("PP_CODE",inputMap.get("ITEMCODE"));
//        iteminfo_data.put("PBO_CODE",inputMap.get("DEMANDCODE"));
//        iteminfo_data.put("PP_NAME",inputMap.get("ITEMNAME"));
//        iteminfo_data.put("PC_CODE",inputMap.get("CONTRACTCODE"));
//        iteminfo_data.put("PC_NAME",inputMap.get("CONTRACTNAME"));
//        iteminfo_data.put("PC_TYPE",inputMap.get("CONTRACTTYPE"));
//        iteminfo_data.put("QX_HT_LYKS_DATE",inputMap.get("SIGNDATE"));
//        iteminfo_data.put("QX_HT_LYJS_DATE",inputMap.get("ENDDATE"));
//        iteminfo_data.put("PC_STATUS",inputMap.get("STATUS"));
//        iteminfo_data.put("FRAM_FLAG",inputMap.get("FRAMEFLAG"));
//        iteminfo_data.put("PP_LX_TYPE",inputMap.get("CREATETYPE"));
//        iteminfo_data.put("IS_HX",inputMap.get("IS_HX"));
//        iteminfo_data.put("QX_HT_QYWC_DATE",inputMap.get("SIGNENDDATE"));
//        iteminfo_data.put("FQLX_DATE",inputMap.get("APPLYDATE"));
//        iteminfo_data.put("WCLX_DATE",inputMap.get("PISUCCESSDATE"));
//
//
////        是否存在后向合同
//        if ("是".equals(inputMap.get("IS_HX"))){
////            是否存在多个后向合同
//            int count = dbq.count("PROJECT_HPG_INFO.HXTTSL", inputMap);
//            if (count>1){
//                iteminfo_data.put("IS_MULTIPLE_HX","是");
//            }
//            else
//                iteminfo_data.put("IS_MULTIPLE_HX","否");
////            后向合同金额
//            Map<String, String> hxtxjezh = dbq.select("PROJECT_HPG_INFO.HXHTZX", inputMap);
//            if (hxtxjezh!=null){
//                iteminfo_data.put("HX_HT_SIGNMNY",hxtxjezh.get("HXTCZH_MNY"));
//            }
////            后向合同编码
//            List<Map<String, String>> HXHTBM = dbq.query("PROJECT_HPG_INFO.HXHTBM", inputMap);
//            if (HXHTBM!=null){
//                StringBuilder hxhtbm_string = new StringBuilder();
//                for (Map<String, String> htbm : HXHTBM) {
//                    hxhtbm_string.append(htbm.get("CONTRACT_CODE"));
//                    hxhtbm_string.append(",");
//                }
//                hxhtbm_string.deleteCharAt(hxhtbm_string.length()-1);
//                iteminfo_data.put("HX_HT_CODE",hxhtbm_string.toString());
//            }
//        }
////        前向合同解析完成时间、收入计划开始时间、收入计划完结时间
//        Map<String, String> QXHTQYWCSJ = dbq.select("PROJECT_HPG_INFO.QXHTQYWCSJ", map);
//        if (QXHTQYWCSJ!=null){
//            iteminfo_data.put("QX_HT_JXWC_DATE",QXHTQYWCSJ.get("SSJH_STARTTIME"));
//            iteminfo_data.put("SRJH_KS_DATE",QXHTQYWCSJ.get("SSJH_STARTTIME"));
//            iteminfo_data.put("SRJH_WJ_DATE",QXHTQYWCSJ.get("SSJH_ENDTIME"));
//        }
//
//
////        收入首次确认时间
//        Map<String, String> SRSCQRSJ = dbq.select("PROJECT_HPG_INFO.SRSCQRSJ", map);
//        if (SRSCQRSJ!=null){
//            iteminfo_data.put("SRQR_SC_DATE",SRSCQRSJ.get("SRSCQRSJ"));
//        }
//
//
////        首次确认收款时间
//        Map<String, String> SCQRSKSJ = dbq.select("PROJECT_HPG_INFO.SCQRSKSJ", map);
//        if (SCQRSKSJ!=null){
//            iteminfo_data.put("SCQRSK_DATE",SCQRSKSJ.get("SCQRSKSJ"));
//        }
//
////        收款计划开始时间
//        Map<String, String> SKJHKSSJ = dbq.select("PROJECT_HPG_INFO.SKJHKSSJ", map);
//        if (SKJHKSSJ!=null){
//            iteminfo_data.put("SKJH_KS_DATE",SKJHKSSJ.get("SKJHKSSJ"));
//        }
//
//        return iteminfo_data;
//    }
//    public  Map<String,String> datatable1(List<Map<String,String>> inputMap){
//        HashMap<String, String> datatable1_data = new HashMap<>();
//        datatable1_data.put("GS_DICT_INCOME",Double.toString(DKButils.toNumeric(inputMap.get(0).get("QPGZ"))+DKButils.toNumeric(inputMap.get(4).get("QPGZ"))));
//        datatable1_data.put("GS_DICT_COST",Double.toString(DKButils.toNumeric(inputMap.get(1).get("QPGZ"))+DKButils.toNumeric(inputMap.get(5).get("QPGZ"))));
//        datatable1_data.put("GS_DICT_MLL",inputMap.get(7).get("QPGZ"));
//        datatable1_data.put("GS_DICT_HSQ",inputMap.get(2).get("QPGZ"));
//        datatable1_data.put("GS_DICT_IRR",inputMap.get(3).get("QPGZ"));
//        datatable1_data.put("QY_DICT_INCOME",Double.toString(DKButils.toNumeric(inputMap.get(0).get("HTQY"))+DKButils.toNumeric(inputMap.get(4).get("HTQY"))));
//        datatable1_data.put("QY_DICT_COST",Double.toString(DKButils.toNumeric(inputMap.get(1).get("HTQY"))+DKButils.toNumeric(inputMap.get(5).get("HTQY"))));
//        datatable1_data.put("QY_DICT_MLL",inputMap.get(7).get("HTQY"));
//        datatable1_data.put("QY_DICT_HSQ",inputMap.get(2).get("HTQY"));
//        datatable1_data.put("QY_DICT_IRR",inputMap.get(3).get("HTQY"));
//
//        datatable1_data.put("SJ_DICT_IRR",inputMap.get(3).get("HPGZ"));
//        datatable1_data.put("SJ_DICT_HSQ",inputMap.get(2).get("HPGZ"));
//
//        datatable1_data.put("QYGS_DICT_INCOME",Double.toString(DKButils.toNumeric(inputMap.get(0).get("QYCB"))+DKButils.toNumeric(inputMap.get(4).get("QYCB"))));
//        datatable1_data.put("QYGS_DICT_COST",Double.toString(DKButils.toNumeric(inputMap.get(1).get("QYCB"))+DKButils.toNumeric(inputMap.get(5).get("QYCB"))));
//
//        datatable1_data.put("QYGS_DICT_MLL",inputMap.get(7).get("QYCB"));
//        datatable1_data.put("QYGS_DICT_HSQ",inputMap.get(2).get("QYCB"));
//        datatable1_data.put("QYGS_DICT_IRR",inputMap.get(3).get("QYCB"));
//
//        datatable1_data.put("ZXJH_JC_INCOME",Double.toString(DKButils.toNumeric(inputMap.get(0).get("ZXPC01"))+DKButils.toNumeric(inputMap.get(4).get("ZXPC01"))));
//        datatable1_data.put("ZXJH_DICT_COST",Double.toString(DKButils.toNumeric(inputMap.get(1).get("ZXPC01"))+DKButils.toNumeric(inputMap.get(5).get("ZXPC01"))));
//
//        datatable1_data.put("ZXJH_JC_MLL",inputMap.get(7).get("ZXPC01"));
//        datatable1_data.put("ZXJH_DICT_HSQ",inputMap.get(2).get("ZXPC01"));
//        datatable1_data.put("ZXJH_DICT_IRR",inputMap.get(3).get("ZXPC01"));
//
//        datatable1_data.put("QPG_HBL_FIRSTYEAR",inputMap.get(8).get("QPGZ"));
//        datatable1_data.put("SJZX_HBL_FIRSTYEAR",inputMap.get(8).get("HPGZ"));
//
//        return datatable1_data;
//
//    }
//    public  Map<String,String> datatable2(List<Map<String,String>> inputMap){
//        HashMap<String, String> datatable2_data = new HashMap<>();
//
//        datatable2_data.put("SRJH_LJ_IDC",inputMap.get(1).get("LJJH"));
//        datatable2_data.put("SRJH_LJ_YUN",inputMap.get(2).get("LJJH"));
//
//        datatable2_data.put("SRJH_LJ_JC",Double.toString(DKButils.toNumeric(inputMap.get(3).get("LJJH"))+DKButils.toNumeric(inputMap.get(4).get("LJJH"))+DKButils.toNumeric(inputMap.get(5).get("LJJH"))+DKButils.toNumeric(inputMap.get(6).get("LJJH"))));
//        datatable2_data.put("SRJH_LJ_DICT",inputMap.get(0).get("LJJH"));
//        datatable2_data.put("SRJH_LJ_SB",inputMap.get(8).get("LJJH"));
//        datatable2_data.put("SRJH_LJ_QT",Double.toString(DKButils.toNumeric(inputMap.get(7).get("LJJH"))+DKButils.toNumeric(inputMap.get(9).get("LJJH"))));
//        datatable2_data.put("SRJH_LJ_ALL",inputMap.get(10).get("LJJH"));
//
//        datatable2_data.put("QRSR_LJ_IDC",inputMap.get(1).get("LJZX"));
//        datatable2_data.put("QRSR_LJ_YUN",inputMap.get(2).get("LJZX"));
//        datatable2_data.put("QRSR_LJ_JC",Double.toString(DKButils.toNumeric(inputMap.get(3).get("LJZX"))+DKButils.toNumeric(inputMap.get(4).get("LJZX"))+DKButils.toNumeric(inputMap.get(5).get("LJZX"))+DKButils.toNumeric(inputMap.get(6).get("LJZX"))));
//        datatable2_data.put("QRSR_LJ_DICT",inputMap.get(0).get("LJZX"));
//        datatable2_data.put("QRSR_LJ_SB",inputMap.get(8).get("LJZX"));
//        datatable2_data.put("QRSR_LJ_QT",Double.toString(DKButils.toNumeric(inputMap.get(7).get("LJZX"))+DKButils.toNumeric(inputMap.get(9).get("LJZX"))));
//        datatable2_data.put("QRSR_LJ_ALL",inputMap.get(10).get("LJZX"));
//
//        datatable2_data.put("SRJH_DN_IDC",inputMap.get(1).get("DNJH"));
//        datatable2_data.put("SRJH_DN_YUN",inputMap.get(2).get("DNJH"));
//        datatable2_data.put("SRJH_DN_JC",Double.toString(DKButils.toNumeric(inputMap.get(3).get("DNJH"))+DKButils.toNumeric(inputMap.get(4).get("DNJH"))+DKButils.toNumeric(inputMap.get(5).get("DNJH"))+DKButils.toNumeric(inputMap.get(6).get("DNJH"))));
//        datatable2_data.put("SRJH_DN_DICT",inputMap.get(0).get("DNJH"));
//        datatable2_data.put("SRJH_DN_SB",inputMap.get(8).get("DNJH"));
//        datatable2_data.put("SRJH_DN_QT",Double.toString(DKButils.toNumeric(inputMap.get(7).get("DNJH"))+DKButils.toNumeric(inputMap.get(9).get("DNJH"))));
//        datatable2_data.put("SRJH_DN_ALL",inputMap.get(10).get("DNJH"));
//
//        datatable2_data.put("QRSR_DN_IDC",inputMap.get(1).get("DNZX"));
//        datatable2_data.put("QRSR_DN_YUN",inputMap.get(2).get("DNZX"));
//        datatable2_data.put("QRSR_DN_JC",Double.toString(DKButils.toNumeric(inputMap.get(3).get("DNZX"))+DKButils.toNumeric(inputMap.get(4).get("DNZX"))+DKButils.toNumeric(inputMap.get(5).get("DNZX"))+DKButils.toNumeric(inputMap.get(6).get("DNZX"))));
//        datatable2_data.put("QRSR_DN_DICT",inputMap.get(0).get("DNZX"));
//        datatable2_data.put("QRSR_DN_SB",inputMap.get(8).get("DNZX"));
//        datatable2_data.put("QRSR_DN_QT",Double.toString(DKButils.toNumeric(inputMap.get(7).get("DNZX"))+DKButils.toNumeric(inputMap.get(9).get("DNZX"))));
//        datatable2_data.put("QRSR_DN_ALL",inputMap.get(10).get("DNZX"));
//
//        datatable2_data.put("CBJH_LJ_DICT",inputMap.get(11).get("LJJH"));
//        datatable2_data.put("CBJH_LJ_SB",inputMap.get(17).get("LJJH"));
//        datatable2_data.put("CBJH_LJ_ALL",inputMap.get(20).get("LJJH"));
//
//
//        datatable2_data.put("QRCB_LJ_DICT",inputMap.get(11).get("LJZX"));
//        datatable2_data.put("QRCB_LJ_SB",inputMap.get(17).get("LJZX"));
//        datatable2_data.put("QRCB_LJ_ALL",inputMap.get(20).get("LJZX"));
//
//        datatable2_data.put("CBJH_DN_DICT",inputMap.get(11).get("DNJH"));
//        datatable2_data.put("CBJH_DN_SB",inputMap.get(17).get("DNJH"));
//        datatable2_data.put("CBJH_DN_ALL",inputMap.get(20).get("DNJH"));
//
//        datatable2_data.put("QRCB_DN_DICT",inputMap.get(11).get("DNZX"));
//        datatable2_data.put("QRCB_DN_SB",inputMap.get(17).get("DNZX"));
//        datatable2_data.put("QRCB_DN_ALL",inputMap.get(20).get("DNZX"));
//
//        datatable2_data.put("ZXJH_IDC_INCOME",inputMap.get(1).get("LJPC"));
//        datatable2_data.put("ZXJH_YUN_INCOME",inputMap.get(2).get("LJPC"));
//
//        return datatable2_data;
//
//    }
//    public  Map<String,String> datatable3(List<Map<String,String>> inputMap){
//        HashMap<String, String> datatable3_data = new HashMap<>();
//        datatable3_data.put("GS_PP_IMCONE",Double.toString(DKButils.toNumeric(inputMap.get(0).get("SJXX"))+DKButils.toNumeric(inputMap.get(4).get("SJXX"))));
//        datatable3_data.put("GS_PP_COST",Double.toString(DKButils.toNumeric(inputMap.get(1).get("SJXX"))+DKButils.toNumeric(inputMap.get(5).get("SJXX"))));
//        datatable3_data.put("GS_PP_MLL",inputMap.get(7).get("SJXX"));
//        datatable3_data.put("GS_ALL_HSQ",inputMap.get(2).get("SJXX"));
//        datatable3_data.put("GS_ALL_IRR",inputMap.get(3).get("SJXX"));
//        datatable3_data.put("QY_PC_INCOME",Double.toString(DKButils.toNumeric(inputMap.get(0).get("HTQY"))+DKButils.toNumeric(inputMap.get(4).get("HTQY"))));
//        datatable3_data.put("QY_PC_COST",Double.toString(DKButils.toNumeric(inputMap.get(1).get("HTQY"))+DKButils.toNumeric(inputMap.get(5).get("HTQY"))));
//        datatable3_data.put("QY_ALL_MLL",inputMap.get(7).get("HTQY"));
//        datatable3_data.put("GS_TZ",inputMap.get(1).get("SJXX"));
//        datatable3_data.put("QY_TZ",inputMap.get(1).get("HTQY"));
//        datatable3_data.put("QY_ALL_HSQ",inputMap.get(2).get("HTQY"));
//        datatable3_data.put("QY_ALL_IRR",inputMap.get(3).get("HTQY"));
//
//        datatable3_data.put("TZ_LJ",inputMap.get(1).get("SJZX"));
//        datatable3_data.put("SJ_ALL_IRR",inputMap.get(3).get("SJZX"));
//        datatable3_data.put("SJ_ALL_HSQ",inputMap.get(2).get("SJZX"));
//
//        datatable3_data.put("QYGS_ALL_INCOME",Double.toString(DKButils.toNumeric(inputMap.get(0).get("QYCB"))+DKButils.toNumeric(inputMap.get(4).get("QYCB"))));
//        datatable3_data.put("QYGS_ALL_COST",Double.toString(DKButils.toNumeric(inputMap.get(1).get("QYCB"))+DKButils.toNumeric(inputMap.get(5).get("QYCB"))));
//
//        datatable3_data.put("QYGS_ALL_MLL",inputMap.get(7).get("QYCB"));
//        datatable3_data.put("QYGS_TZ_MLL",inputMap.get(1).get("QYCB"));
//        datatable3_data.put("QYGS_ALL_HSQ",inputMap.get(2).get("QYCB"));
//        datatable3_data.put("QYGS_ALL_IRR",inputMap.get(3).get("QYCB"));
//
//        datatable3_data.put("ZXJH_ALL_INCOME",Double.toString(DKButils.toNumeric(inputMap.get(0).get("ZXPC01"))+DKButils.toNumeric(inputMap.get(4).get("ZXPC01"))));
//        datatable3_data.put("ZXJH_ALL_COST",Double.toString(DKButils.toNumeric(inputMap.get(1).get("ZXPC01"))+DKButils.toNumeric(inputMap.get(5).get("ZXPC01"))));
//
//        datatable3_data.put("ZXJH_ALL_MLL",inputMap.get(7).get("ZXPC01"));
//        datatable3_data.put("ZXJH_TZ",inputMap.get(1).get("ZXPC01"));
//        datatable3_data.put("ZXJH_ALL_HSQ",inputMap.get(2).get("ZXPC01"));
//
//        datatable3_data.put("ZXJH_ALL_IRR",inputMap.get(3).get("ZXPC01"));
//
//        datatable3_data.put("JHLZ_JC_INCOME_PERCENT",inputMap.get(11).get("SJXX"));
//        datatable3_data.put("JHLZ_JC_COST_PERCENT",inputMap.get(12).get("SJXX"));
//        datatable3_data.put("JHLZ_JC_PERCENT",Double.toString(DKButils.toNumeric(inputMap.get(11).get("SJXX"))-DKButils.toNumeric(inputMap.get(12).get("SJXX"))));
//
//        datatable3_data.put("SJLZ_JC_INCOME_PERCENT",inputMap.get(11).get("SJZX"));
//        datatable3_data.put("SJLZ_JC_COST_PERCENT",inputMap.get(12).get("SJZX"));
//        datatable3_data.put("SJLZ_JC_PERCENT",Double.toString(DKButils.toNumeric(inputMap.get(11).get("SJZX"))-DKButils.toNumeric(inputMap.get(12).get("SJZX"))));
//
//        datatable3_data.put("FXZB_IS_QF",inputMap.get(23).get("QTXX"));
//        datatable3_data.put("FXZB_IS_CF",inputMap.get(24).get("QTXX"));
//        datatable3_data.put("FXZB_IS_SZYZ",inputMap.get(26).get("QTXX"));
//        datatable3_data.put("FXZB_IS_LSBPP",inputMap.get(27).get("QTXX"));
//
//        return datatable3_data;
//    }
//    public  Map<String,String> datatable4(List<Map<String,String>> inputMap){
//        HashMap<String, String> datatable4_data = new HashMap<>();
//        datatable4_data.put("ZQKP_KPMNY",inputMap.get(0).get("KPJE"));
//        datatable4_data.put("ZQKP_RL_MNY",inputMap.get(0).get("KHJE"));
//        return datatable4_data;
//    }
//
//    //获取项目应收账款表的值
//    public  Map<String,String> xmyszkb(Map<String,String> inputMap) throws Exception {
//        HashMap<String, String> datatable5_data = new HashMap<>();
//        datatable5_data.put("XZQYZ_SRJH_LJ",inputMap.get("SR_YS_ALL"));
//        datatable5_data.put("XZQYZ_SRJH_DN",inputMap.get("SR_YS_DN"));
//        datatable5_data.put("XZQYZ_SRQR_LJ",inputMap.get("SS_ALL"));
//        datatable5_data.put("XZQYZ_SRQR_DN",inputMap.get("SS_DN"));
//
//        datatable5_data.put("XZHYZ_SRJH_LJ",inputMap.get("HT_XZ"));
//        datatable5_data.put("XZHYZ_SRQR_LJ",inputMap.get("HT_SR"));
//        return datatable5_data;
//    }
//
//    //获取项目当月信息
//    public  Map<String,String> xmdyxx(Map<String,String> inputMap) throws Exception {
//        List<String> param = new ArrayList<>();
//        //临时表表名
//        String tableName = "";
//        HashMap<String, String> datatable6_data = new HashMap<>();
////        Map<String,String> map = (Map<String, String>) dbp.callProc("producturename", param);
//        try {
//            String sql = "(\n" +
//                    "\tjhsr_dict numeric(15,2),\n" +
//                    "    jhsr_idc numeric(15,2),\n" +
//                    "\tjhsr_yun numeric(15,2),\n" +
//                    "\tjhsr_ptdsj numeric(15,2),\n" +
//                    "\tjhsr_ptzw numeric(15,2),\n" +
//                    "\tjhsr_wlw numeric(15,2),\n" +
//                    "\tjhsr_hlwjr numeric(15,2),\n" +
//                    "\tjhsr_hlwzx numeric(15,2),\n" +
//                    "\tjhsr_sbxs numeric(15,2),\n" +
//                    "\tjhsr_qtwy numeric(15,2),\n" +
//                    "\tjhsr_all numeric(15,2),\n" +
//                    "\n" +
//                    "\tjhzx_dict numeric(15,2),\n" +
//                    "    jhzx_idc numeric(15,2),\n" +
//                    "\tjhzx_yun numeric(15,2),\n" +
//                    "\tjhzx_ptdsj numeric(15,2),\n" +
//                    "\tjhzx_ptzw numeric(15,2),\n" +
//                    "\tjhzx_wlw numeric(15,2),\n" +
//                    "\tjhzx_hlwjr numeric(15,2),\n" +
//                    "\tjhzx_hlwzx numeric(15,2),\n" +
//                    "\tjhzx_sbxs numeric(15,2),\n" +
//                    "\tjhzx_qtwy numeric(15,2),\n" +
//                    "\tjhzx_all numeric(15,2),\n" +
//                    "\n" +
//                    "    cbjh_dict numeric(15,2),\n" +
//                    "    cbjh_sbzc numeric(15,2),\n" +
//                    "\n" +
//                    "    cbzx_dict numeric(15,2),\n" +
//                    "    cbzx_sbzc numeric(15,2),\n" +
//                    "    is_flag  varchar(21)\n" +
//                    ")";
//
//            //创建临时表
//            String suffix = Integer.toString(dbc.hashCode());
//            tableName = ictPmReport.createTempTable(suffix+"_current_year", sql);
//            param.add(tableName);
//            param.add(inputMap.get("ITEMID"));
//            param.add(inputMap.get("ITEMCODE"));
//            param.add(inputMap.get("PERIOD"));
//            dbp.callProc("get_dkb_currentmonth",param);
//            Map<String,String> hashMap = new HashMap();
//            hashMap.put("tablename",tableName);
//            Map<String, String> result_data = dbq.selectBySql("select * from {p:tablename}", hashMap);
//            datatable6_data.put("SRJH_DY_IDC",result_data.get("JHSR_IDC"));
//            datatable6_data.put("SRJH_DY_YUN",result_data.get("JHSR_YUN"));
//            datatable6_data.put("SRJH_DY_JC",Double.toString(DKButils.toNumeric(result_data.get("JHSR_PTDSJ"))+DKButils.toNumeric(result_data.get("JHSR_PTZW"))+DKButils.toNumeric(result_data.get("JHSR_WLW"))+DKButils.toNumeric(result_data.get("JHSR_HLWJR"))));
//            datatable6_data.put("SRJH_DY_DICT",result_data.get("JHSR_DICT"));
//            datatable6_data.put("SRJH_DY_SB",result_data.get("JHSR_SBXS"));
//            datatable6_data.put("SRJH_DY_QT",Double.toString(DKButils.toNumeric(result_data.get("JHSR_HLWZX"))+DKButils.toNumeric(result_data.get("JHSR_QTWY"))));
//            datatable6_data.put("SRJH_DY_ALL",result_data.get("JHSR_ALL"));
//
//
//            datatable6_data.put("QRSR_DY_IDC",result_data.get("JHZX_IDC"));
//            datatable6_data.put("QRSR_DY_YUN",result_data.get("JHZX_YUN"));
//            datatable6_data.put("QRSR_DY_JC",Double.toString(DKButils.toNumeric(result_data.get("JHZX_PTDSJ"))+DKButils.toNumeric(result_data.get("JHZX_PTZW"))+DKButils.toNumeric(result_data.get("JHZX_WLW"))+DKButils.toNumeric(result_data.get("JHZX_HLWJR"))));
//            datatable6_data.put("QRSR_DY_DICT",result_data.get("JHZX_DICT"));
//            datatable6_data.put("QRSR_DY_SB",result_data.get("JHZX_SBXS"));
//            datatable6_data.put("QRSR_DY_QT",Double.toString(DKButils.toNumeric(result_data.get("JHZX_HLWZX"))+DKButils.toNumeric(result_data.get("JHZX_QTWY"))));
//            datatable6_data.put("QRSR_DY_ALL",result_data.get("JHZX_ALL"));
//
//            datatable6_data.put("CBJH_DY_DICT",result_data.get("CBJH_DICT"));
//            datatable6_data.put("CBJH_DY_SB",result_data.get("CBJH_SBZC"));
//            datatable6_data.put("CBJH_DY_ALL",Double.toString(DKButils.toNumeric(result_data.get("CBJH_DICT"))+DKButils.toNumeric(result_data.get("CBJH_SBZC"))));
//
//            datatable6_data.put("QRCB_DY_DICT",result_data.get("CBZX_DICT"));
//            datatable6_data.put("QRCB_DY_SB",result_data.get("CBZX_SBZC"));
//            datatable6_data.put("QRCB_DY_ALL",Double.toString(DKButils.toNumeric(result_data.get("CBZX_DICT"))+DKButils.toNumeric(result_data.get("CBZX_SBZC"))));
//
//
//        }catch (Exception  e){
//            e.printStackTrace();
//        }finally {
//            ictPmReport.dropTempTable(tableName);
//        }
//        return datatable6_data;
//    }
//
//
//    public Map<String, String> qtxx(Map<String, String> inputMap) throws SQLException {
//        //    当年投资总额
//        Map<String, String> dy_tz = dbq.selectBySql("select coalesce(sum(mny-tax),0) as tz_dy from cost_data a where a.itemid={n:ITEMID} and type='TZ' and ny<={PERIOD}  and substring({PERIOD},0,5)= substring(ny,0,5)", inputMap);
//        HashMap<String, String> datatable7_data = new HashMap<>();
//        datatable7_data.put("TZ_DN",dy_tz.get("TZ_DN"));
////        累计应收（基于收款计划）
//        Map<String, String> ljsk_skjh = dbq.selectBySql("select sum(persum) as ljsk from payment_plan_merge ppm where itemid = {p:ITEMID}", inputMap);
//        datatable7_data.put("XZQYZ_INCOME_LJ",ljsk_skjh.get("LJSK"));
////       当年应收（基于收款计划）
//        Map<String, String> dnsk_skjh = dbq.selectBySql("select sum(persum) as dnsk from payment_plan_merge ppm where itemid = {p:ITEMID}  and substring({PERIOD},0,5)= substring(ppm.planperdate,0,5) \n" +
//                "and {PERIOD}<=substring(ppm.planperdate,0,8)", inputMap);
//        datatable7_data.put("XZQYZ_INCOME_DN",dnsk_skjh.get("DNSK"));
////        累计收款
//        Map<String, String> ljsk = dbq.selectBySql("select sum(da.mny) as ljsk  from income_data da where da.itemid = {p:ITEMID} and da.dt='SS';", inputMap);
//        datatable7_data.put("LJSK",ljsk.get("LJSK"));
////        当年收款
//        Map<String, String> dnsk = dbq.selectBySql("select sum(da.mny) as dnsk  from income_data da where da.itemid = {p:ITEMID} and da.dt='SS' and {PERIOD}<=da.datamonth \n" +
//                "and substring({PERIOD},0,5)  = substring(da.datamonth ,0,5)", inputMap);
//        datatable7_data.put("DNSK",dnsk.get("DNSK"));
//
//
////     当年应收（基于收款计划）	XZHYZ_INCOME_DN
//        datatable7_data.put("XZHYZ_INCOME_DN",Double.toString(DKButils.toNumeric(datatable7_data.get("XZQYZ_INCOME_DN")) -DKButils.toNumeric(datatable7_data.get("DNSK"))) );
//
////     累计应收（基于收款计划）	XZHYZ_INCOME_LJ
//        datatable7_data.put("XZHYZ_INCOME_LJ",Double.toString(DKButils.toNumeric(datatable7_data.get("XZQYZ_INCOME_LJ")) -DKButils.toNumeric(datatable7_data.get("LJSK"))) );
//
////     是否战略性项目
///*        pm_project表无数据，暂时注释掉
//        Map<String, String> is_zlx = dbq.selectBySql("select case when pp_is_zlxm = 0 then '否' when pp_is_zlxm = 1 then '是' end as is_zlx from pm_project where project_id = {p:ITEMID}", inputMap);
//        datatable7_data.put("IS_ZLX",is_zlx.get("IS_ZLX"));*/
//        return datatable7_data;
//    }
//
//
//
//
//    //    检测是否为数字
//    public static double toNumeric(String str) {
//        if ("".equals(str) || str == null)
//            return 0;
//        double d;
//        try {
//            d = Double.parseDouble(str);
//        } catch (NumberFormatException nfe) {
//            return 0;
//        }
//        return d;
//    }
//
//
//}
