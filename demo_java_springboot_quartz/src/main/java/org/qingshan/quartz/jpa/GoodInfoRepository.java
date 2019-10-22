package org.qingshan.quartz.jpa;

import org.qingshan.quartz.entity.GoodInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodInfoRepository extends JpaRepository<GoodInfoEntity,Long> {
}
