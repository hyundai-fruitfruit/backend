package com.hyundai.app.guide;

import com.hyundai.app.guide.domain.Guide;
import com.hyundai.app.guide.domain.HashtagCategory;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/03/09
 * (설명)
 */
public interface GuideMapper {

    List<Guide> getGuideAll();
    List<HashtagCategory> getHashtagCategoryAll();
}