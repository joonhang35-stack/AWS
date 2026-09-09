package com.bcs.zsg.common.helper.deserializer;

import java.lang.reflect.Type;

import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.common.helper.DatesUtils;
import com.bcs.zsg.common.helper.JsonGsonUtils;
import com.bcs.zsg.crm.sec.vo.KeycloakUserAttributeVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonKeycloakUserAttrDeserializer implements JsonDeserializer<KeycloakUserAttributeVO>, JsonSerializer<KeycloakUserAttributeVO> {

    @Override
    public KeycloakUserAttributeVO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();

        KeycloakUserAttributeVO vo = new KeycloakUserAttributeVO();
        
        vo.setIdentity(first(obj, "identity"));
        vo.setNationality(first(obj, "nationality"));

        PersonVO personVO = new PersonVO();
//        personVO.setCountryId(toLong(first(obj, "nationality")));
        personVO.setNric(first(obj, "identity"));
        personVO.setNickName(first(obj, "nickname"));
        personVO.setDob(DatesUtils.toDate(first(obj, "dob")));

        AddressVO mailAddrVO = new AddressVO();
        mailAddrVO.setAddr1(first(obj, "mail_addr_1"));
        mailAddrVO.setAddr2(first(obj, "mail_addr_2"));
        mailAddrVO.setAddr3(first(obj, "mail_addr_3"));

        vo.setPersonVO(personVO);
        vo.setMailAddrVO(mailAddrVO);
        vo.setBillAddrVO(new AddressVO());

        return vo;
    }
    
    @Override
    public JsonElement serialize(KeycloakUserAttributeVO src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        if (src != null) {
        	PersonVO personVO = src.getPersonVO();
            AddressVO mailAddrVO = src.getMailAddrVO();
            AddressVO billAddrVO = src.getBillAddrVO();

            addArrayValue(obj, "nationality", src.getNationality());
            addArrayValue(obj, "identity", src.getIdentity());
            
            addArrayValue(obj, "nickname", personVO == null ? null : personVO.getNickName());
            addArrayValue(obj, "dob", DatesUtils.formatDate(personVO == null ? null : personVO.getDob(), "yyyy-MM-dd"));

            addArrayValue(obj, "bill_addr_1", billAddrVO == null ? null : billAddrVO.getAddr1());
            addArrayValue(obj, "bill_addr_2", billAddrVO == null ? null : billAddrVO.getAddr2());
            addArrayValue(obj, "bill_addr_3", billAddrVO == null ? null : billAddrVO.getAddr3());
            
            addArrayValue(obj, "mail_addr_1", mailAddrVO == null ? null : mailAddrVO.getAddr1());
            addArrayValue(obj, "mail_addr_2", mailAddrVO == null ? null : mailAddrVO.getAddr2());
            addArrayValue(obj, "mail_addr_3", mailAddrVO == null ? null : mailAddrVO.getAddr3());
        }

        return obj;
    }

    private String first(JsonObject obj, String key) {
        return JsonGsonUtils.first(obj, key);
    }

    private Long toLong(String value) {
        if (StringUtils.isBlank(value))	return null;
        return Long.valueOf(value);
    }
    
    private void addArrayValue(JsonObject obj, String key, String value) {
        if (StringUtils.isBlank(value))	return;
        
        JsonArray arr = new JsonArray();
        arr.add(value);
        obj.add(key, arr);
    }
}