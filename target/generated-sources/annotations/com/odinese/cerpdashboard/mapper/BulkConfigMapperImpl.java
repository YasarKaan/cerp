package com.odinese.cerpdashboard.mapper;

import com.odinese.cerpdashboard.dtos.BulkConfigDto;
import com.odinese.cerpdashboard.dtos.ConfigTargetMachineDto;
import com.odinese.cerpdashboard.model.ConfigDetailsProgress;
import com.odinese.cerpdashboard.model.MBulkConfig;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-08T15:07:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class BulkConfigMapperImpl implements BulkConfigMapper {

    @Override
    public BulkConfigDto toDto(MBulkConfig mBulkConfig) {
        if ( mBulkConfig == null ) {
            return null;
        }

        BulkConfigDto bulkConfigDto = new BulkConfigDto();

        bulkConfigDto.setId( mBulkConfig.getId() );
        bulkConfigDto.setVendorName( mBulkConfig.getVendorName() );
        bulkConfigDto.setConfigJSON( mBulkConfig.getConfigJSON() );
        if ( mBulkConfig.getCreatedAt() != null ) {
            bulkConfigDto.setCreatedAt( new Timestamp( mBulkConfig.getCreatedAt().getTime() ) );
        }
        bulkConfigDto.setFwUpgrade( mBulkConfig.isFwUpgrade() );

        bulkConfigDto.setConfigTargetMachines( convertConfigTargetMachines(mBulkConfig) );

        return bulkConfigDto;
    }

    @Override
    public ConfigTargetMachineDto toDto(MBulkConfig.ConfigTargetMachine configTargetMachine) {
        if ( configTargetMachine == null ) {
            return null;
        }

        ConfigTargetMachineDto configTargetMachineDto = new ConfigTargetMachineDto();

        configTargetMachineDto.setId( configTargetMachine.getId() );
        configTargetMachineDto.setIpAddress( configTargetMachine.getIpAddress() );
        configTargetMachineDto.setConfigDetailsProgresses( configDetailsProgressListToConfigDetailsProgressList( configTargetMachine.getConfigDetailsProgresses() ) );

        return configTargetMachineDto;
    }

    protected ConfigDetailsProgress configDetailsProgressToConfigDetailsProgress(MBulkConfig.ConfigDetailsProgress configDetailsProgress) {
        if ( configDetailsProgress == null ) {
            return null;
        }

        ConfigDetailsProgress configDetailsProgress1 = new ConfigDetailsProgress();

        configDetailsProgress1.setId( configDetailsProgress.getId() );
        configDetailsProgress1.setConfigField( configDetailsProgress.getConfigField() );
        configDetailsProgress1.setJobId( configDetailsProgress.getJobId() );
        configDetailsProgress1.setJobStatus( configDetailsProgress.getJobStatus() );
        if ( configDetailsProgress.getCreatedAt() != null ) {
            configDetailsProgress1.setCreatedAt( new Timestamp( configDetailsProgress.getCreatedAt().getTime() ) );
        }

        return configDetailsProgress1;
    }

    protected List<ConfigDetailsProgress> configDetailsProgressListToConfigDetailsProgressList(List<MBulkConfig.ConfigDetailsProgress> list) {
        if ( list == null ) {
            return null;
        }

        List<ConfigDetailsProgress> list1 = new ArrayList<ConfigDetailsProgress>( list.size() );
        for ( MBulkConfig.ConfigDetailsProgress configDetailsProgress : list ) {
            list1.add( configDetailsProgressToConfigDetailsProgress( configDetailsProgress ) );
        }

        return list1;
    }
}
