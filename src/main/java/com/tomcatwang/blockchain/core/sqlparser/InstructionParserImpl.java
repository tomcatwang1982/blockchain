package com.tomcatwang.blockchain.core.sqlparser;

import com.tomcatwang.blockchain.block.Instruction;
import com.tomcatwang.blockchain.block.InstructionBase;
import com.tomcatwang.blockchain.common.FastJsonUtil;
import com.tomcatwang.blockchain.core.model.base.BaseEntity;
import com.tomcatwang.blockchain.core.model.convert.ConvertTableName;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 将区块内指令解析并入库
 * @author tomcatwang wrote on 2019/08/19.
 */
@Service
public class InstructionParserImpl<T extends BaseEntity> implements InstructionParser {
    @Resource
    private ConvertTableName<T> convertTableName;
    @Resource
    private AbstractSqlParser<T>[] sqlParsers;

    @Override
    public boolean parse(InstructionBase instructionBase) {
        byte operation = instructionBase.getOperation();
        String table = instructionBase.getTable();
        String json = instructionBase.getOldJson();
        //表对应的类名，如MessageEntity.class
        Class<T> clazz = convertTableName.convertOf(table);
        T object = FastJsonUtil.toBean(json, clazz);
        for (AbstractSqlParser<T> sqlParser : sqlParsers) {
            if (clazz.equals(sqlParser.getEntityClass())) {
            	if(instructionBase instanceof Instruction){
            		object.setPublicKey(((Instruction)instructionBase).getPublicKey());
            	}
                sqlParser.parse(operation, instructionBase.getInstructionId(), object);
                break;
            }
        }

        return true;
    }
}
