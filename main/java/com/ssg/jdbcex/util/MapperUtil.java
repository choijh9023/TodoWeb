package com.ssg.jdbcex.util;
/**
 * 24년 수업 시작
 */

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

// ModelMapper를 이용한 객체 간 매핑을 도와주는 유틸리티 클래스
public enum MapperUtil {
    INSTANCE; // Singleton 패턴을 활용하여 유일한 인스턴스를 제공

    private ModelMapper modelMapper; // ModelMapper 인스턴스

    // 생성자에서 ModelMapper 초기화 및 설정
    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) // 필드 매칭 활성화
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE) // private 필드에 접근 허용
                .setMatchingStrategy(MatchingStrategies.STRICT); // 엄격한 일치 전략 사용
    }

    // ModelMapper 인스턴스를 반환하는 메서드
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
