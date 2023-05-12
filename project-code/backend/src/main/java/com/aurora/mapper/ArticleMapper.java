package com.aurora.mapper;

import com.aurora.model.dto.ArticleAdminDTO;
import com.aurora.model.dto.ArticleCardDTO;
import com.aurora.model.dto.ArticleDTO;
import com.aurora.model.dto.ArticleStatisticsDTO;
import com.aurora.entity.Article;
import com.aurora.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//这是一个数据访问层（DAO）的接口，名为ArticleMapper，继承自BaseMapper<Article>。
// 在该接口中定义了许多操作数据库的方法，比如插入文章、删除文章、更新文章等等，通过调用
// 这些方法可以实现对数据库中文章的增删改查操作。该接口使用了@Repository注解，标识它
// 是一个用于数据访问的组件，可以被其他组件注入使用。
//进入resource/mapper目录可以看到与这些Mapper同名的xml文件，里面写了对应函数所执行的sql语句
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleCardDTO> listTopAndFeaturedArticles();

    List<ArticleCardDTO> listArticles(@Param("current") Long current, @Param("size") Long size);

    List<ArticleCardDTO> getArticlesByCategoryId(@Param("current") Long current, @Param("size") Long size, @Param("categoryId") Integer categoryId);

    ArticleDTO getArticleById(@Param("articleId") Integer articleId);

    ArticleCardDTO getPreArticleById(@Param("articleId") Integer articleId);

    ArticleCardDTO getNextArticleById(@Param("articleId") Integer articleId);

    ArticleCardDTO getFirstArticle();

    ArticleCardDTO getLastArticle();

    List<ArticleCardDTO> listArticlesByTagId(@Param("current") Long current, @Param("size") Long size, @Param("tagId") Integer tagId);

    List<ArticleCardDTO> listArchives(@Param("current") Long current, @Param("size") Long size);

    Integer countArticleAdmins(@Param("conditionVO") ConditionVO conditionVO);

    List<ArticleAdminDTO> listArticlesAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

    List<ArticleStatisticsDTO> listArticleStatistics();

}

