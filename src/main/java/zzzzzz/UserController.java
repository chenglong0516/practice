//package zzzzzz;
//
//public class UserController {
//    /**
//     * @Description:判断用户是否为事实客户，如果是，将事实客户与入参理财师工号，加密返回
//     * 测试环境示例 https://wap.chtfundtest.com/recommend/views/newRecommend.html?url=22ab7feb2702f8b1ee743e4b87c7f51b4553d80a71d0c2adef62af69be30f0c5ae91886752601ac12ec84090799d082a180cc1a96b985ab0594bf18dd4e69e69d91da2acf973c62294f63d6cd0786141
//     * 预生产环境示例 https://wap.haomaojf.com/recommend/views/newRecommend.html?url=22ab7feb2702f8b1ee743e4b87c7f51b4553d80a71d0c2adef62af69be30f0c5ae91886752601ac12ec84090799d082a180cc1a96b985ab0594bf18dd4e69e69d91da2acf973c62294f63d6cd0786141
//     * 生产环境示例 https://wap.chtfund.com/recommend/views/newRecommend.html?url=22ab7feb2702f8b1ee743e4b87c7f51b4553d80a71d0c2adef62af69be30f0c5ae91886752601ac12ec84090799d082a180cc1a96b985ab0594bf18dd4e69e69d91da2acf973c62294f63d6cd0786141
//     * @author shl
//     * @param json
//     * @param session
//     * @return
//     */
//    @RequestMapping("/oldRecommendNew.action")
//    @AuditLog(description = "老带新校验")
//    @TrackLog(description = "老带新校验", channel = "PC")
//    public @ResponseBody CommonAppResponseBody<Object> oldRecommendNew(@RequestBody String json, HttpSession session){
//        CommonAppResponseBody<Object> responseBody = getAppResponseBody();
//        OldRecommendNewResult oldRecommendNewResult = new OldRecommendNewResult();
//        try {
//            //登录参数
//            OldRecommendNewParams oldRecommendNewParams = jsonConvertToBean(json, OldRecommendNewParams.class);
//            //客户信息
//            UserInfo userInfo = (UserInfo) session.getAttribute(BigdataConstants.SESSION_USER_KEY);
//            //判断是否实名认证
//            if(BigdataConstants.CERTIFICATION_FLAG_YES.equals(userInfo.getIs_certification())){//事实客户，可以推荐
//                //产生加密串
//                logger.debug("老带新校验---老客户编号:{}, 老客户统一客户编号:{}, 老客户理顾号:{}",userInfo.getUser_id(),userInfo.getCustomerNo(),oldRecommendNewParams.getBrokerAccount());
//                UserIdAndBrokerNo userIdAndBrokerNo = new UserIdAndBrokerNo();
//                userIdAndBrokerNo.setUserId(userInfo.getUser_id());
//                userIdAndBrokerNo.setCustomerNo(userInfo.getCustomerNo());
//                userIdAndBrokerNo.setBrokerNo(oldRecommendNewParams.getBrokerAccount());
//                JSONObject jsonObject = JSONObject.fromObject(userIdAndBrokerNo);
//                //加密例子 "123456" -- "953256efa62d9e990f97d0e684d962d0"
//                String aesEncrypt = AESSimpleUtils.aesEncrypt(jsonObject.toString(), "Qsj9oRf10hG8lJpbrT1kbg==");
//                //封装返回bean
//                oldRecommendNewResult.setRecommendable("0");
//                oldRecommendNewResult.setAesEncrypt(aesEncrypt);
//            }else{//未实名认证，不能推荐
//                oldRecommendNewResult.setRecommendable("1");
//            }
//            responseBody.setData(oldRecommendNewResult);
//        } catch (TransformJsonParamsException e) {
//            logger.error("[老带新校验]-json转化出现异常：", e);
//            responseBody = defaultFail(e.getMsg(), e.getCode());
//        } catch (ValidateParamsException e) {
//            logger.error("[老带新校验]-返回code:{}|msg:{}", e.getCode(), e.getMsg());
//            responseBody = defaultFail(e.getMsg(), e.getCode());
//        } catch (Exception e) {
//            logger.error("[老带新校验]-系统异常：", e);
//            responseBody = defaultFail();
//        }
//        return responseBody;
//    }
//    
//    /** 
//     * CreateDate:2017年6月20日 
//     * @Description: 老带新返回待加密的bean
//     * @author:chenglong
//     * @version V1.0   
//     */
//
//    public class UserIdAndBrokerNo implements Serializable {
//        
//        private static final long serialVersionUID = -100491239353169639L;
//
//        private String userId;  //客户编号
//        
//        private String customerNo;  //统一客户编号
//        
//        private String brokerNo;    //理财师编号
//
//        public String getUserId() {
//            return userId;
//        }
//
//        public void setUserId(String userId) {
//            this.userId = userId;
//        }
//
//        public String getBrokerNo() {
//            return brokerNo;
//        }
//
//        public void setBrokerNo(String brokerNo) {
//            this.brokerNo = brokerNo;
//        }
//
//        public String getCustomerNo() {
//            return customerNo;
//        }
//
//        public void setCustomerNo(String customerNo) {
//            this.customerNo = customerNo;
//        }
//        
//    }
//}
