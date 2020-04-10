package com.adminapp.demo;

import java.lang.String;

public class ClientDemo{
    public static void main(String[] args) {
        VoicePrintApi obj = new VoicePrintApi("203793248", "qhntfvf4x2a582059ji1z9vzlpr9r2cu");
        try {         
            if (obj.getAccess()) {
            //    String res = obj.getAlgoList();
//                String res = obj.uploadFile("voiceFile", "C:/Users/tjh/Desktop/VOICE/wav/yyh.wav", 300000000);

//                String res = obj.createVpstore("ivector-1-iv_tx_8k-1");
                //String res = obj.getVpstoreList(0, 0);

//                String[] fileIds = {"1584433656078_wNhcYBufIe_voiceFile","1584433917236_UeSDyInTwV_voiceFile","1584436322951_sjsgvagJfq_voiceFile"};
//                String res = obj.registerVoicePrint("ed5392a4-9700-4511-9331-43de304a7310", "yinyonghao", fileIds, true);//
//                String res = obj.getVoicePrintList("a58dc17b-92c6-4300-aef3-3c4b95f98aac", 0, 0);

                String[] vpIds = {"1584433656078_wNhcYBufIe_voiceFile","1584433917236_UeSDyInTwV_voiceFile","1584436322951_sjsgvagJfq_voiceFile"};
                String res = obj.compareVoicePrint("ed5392a4-9700-4511-9331-43de304a7310", "1584431070572_kHKPRJJWaD_voiceFile", vpIds);

                System.out.println(res);
            }
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
}
