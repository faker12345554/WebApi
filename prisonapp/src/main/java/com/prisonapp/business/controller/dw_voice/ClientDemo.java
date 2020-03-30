package com.prisonapp.business.controller.dw_voice;

import com.common.common.result.ResultSet;
import com.prisonapp.token.tation.UserLoginToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

public class ClientDemo {
    public static void main(String[] args) {
        VoicePrintApi obj = new VoicePrintApi("203793248", "qhntfvf4x2a582059ji1z9vzlpr9r2cu");
        try {
            if (obj.getAccess()) {
//                String res = obj.getAlgoList();
//                String res = obj.uploadFile("voiceFile", "C:/Users/tjh/Desktop/VOICE/wav/20200313_113334.wav", 300000000);

//                String res = obj.createVpstore("ivector-1-iv_tx_8k-1");
                //String res = obj.getVpstoreList(0, 0);

                String[] fileIds = {"1585207709577_LKscmGSYXF_voiceFile"};
                String res = obj.registerVoicePrint("d448c490-c065-4627-b957-fa84b9a7a3cb", "tangjihaoaoaoa", fileIds, true);//
//                String res = obj.getVoicePrintList("a58dc17b-92c6-4300-aef3-3c4b95f98aac", 0, 0);
//
//                String[] vpIds = {"1584433656078_wNhcYBufIe_voiceFile","1584433917236_UeSDyInTwV_voiceFile","1584436322951_sjsgvagJfq_voiceFile"};
//                String res = obj.compareVoicePrint("ed5392a4-9700-4511-9331-43de304a7310", "1584431070572_kHKPRJJWaD_voiceFile", vpIds);

                System.out.println(res);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @UserLoginToken
    @ApiOperation(value = " 上传录音文件")
    @PostMapping("/upload")
    public ResultSet upload() {
        ResultSet resultSet = new ResultSet();
        String res="";
        VoicePrintApi obj = new VoicePrintApi("203793248", "qhntfvf4x2a582059ji1z9vzlpr9r2cu");
        try {
            if (obj.getAccess()) {
                res = obj.uploadFile("voiceFile", "C:/Users/tjh/Desktop/VOICE/wav/yyh.wav", 300000000);//上传录音文件
                System.out.println(res);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        resultSet.resultCode = 0;
        resultSet.resultMsg = "";
        resultSet.data = res;
        return resultSet;
    }

}
