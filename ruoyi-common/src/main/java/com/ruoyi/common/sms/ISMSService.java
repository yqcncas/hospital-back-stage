package com.ruoyi.common.sms;

public interface ISMSService {
    static final String privateKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKBPe7ivl3ccfScUdyJIWsqPttzZxNOmonAujmhOsbkTISI0CGjcfi+LnK4GEX8DxWrrhdLeaMDAfWXFa37K9c0qIOE4qtFRwdXPD8WxSnQAYGOWr6GdeObulpT2Q7VpCjruQzUdu6Qy5BGGyEEmwPn8WwVAdREw8hKEikcQyNSVAgMBAAECgYAjAZhLuRRDL1Zo/UASnWguCXnt+iPP7/hStOfmASEv/+xhUqlvdI6dhUgefDa/3Kv0cB/V7zlCtgXr/lM96cYIwXA1ZMC3zorELfzRr5splzqwjvYKBkpJDV0BeSa+IuBmujhDmHy/vyl7+LajOrKggxkenYSMFHYxvY0S0Y+/VQJBANnOjL5DsgQjrEHmev1F8dwqOjY0/dtapauEzu+PNUxE3hDEKNYTtI0+5ArPTt2NqwRKRZkOIlvsdXsLEE6xL2MCQQC8a+U7M4pJ55lSeD0ndCnx55Bm7RJWjNEOAvcFAHeRjeXqICdU3ouHEeaNsFtI08yDp5srDq3X9/YEq5/AWNmnAkEAgJwQS0DZ2svdqUQZF3i3qdNdcCi2RVIvRSo0rwwW4qQ/pBLQMiiMkmKycReccAJ5Gx1oH0pTS4hCjpcn1++UFQJBALkL0c4JeRfJ31cSBSU5PXR4seWTkH31v95d5RFBeyw0yobb2yTLhwY4+09N0ui8MU9fDNIFkY/X0wKVNsglumMCQQCjERPgkq3BLavxl5/VhKYT5afYFzXbC+lrSeaBSYPGM+SK1Rcf7WdiM8Ey7VdjwjQzKxEIo3QdVCzAH+40wV20";

    String sendCode(String mobile, Integer length) throws Exception;

    String sendGroupMsg(String[] mobiles, String[] msg) throws Exception;

    /**
     * 校验手机验证码 验证失败返回true
     * @param mobile
     * @param checkCode
     * @return
     */
    boolean checkSmsCode(String mobile, String checkCode);

    /**
     * 获取随机验证码
     */
    String getRandomCode(int length);
}
