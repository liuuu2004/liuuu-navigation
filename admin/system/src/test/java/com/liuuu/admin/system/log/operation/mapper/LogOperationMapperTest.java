package com.liuuu.admin.system.log.operation.mapper;

import com.liuuu.admin.system.log.operation.dto.LogOperationPageDTO;
import com.liuuu.admin.system.log.operation.vo.LogOperationVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.apache.ibatis.io.Resources;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.Assert.assertNotNull;


@SpringBootTest
public class LogOperationMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void setUp() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

        RunScript.execute(connection, new InputStreamReader(getClass().getResourceAsStream("/schema.sql"), StandardCharsets.UTF_8));
        RunScript.execute(connection, new InputStreamReader(getClass().getResourceAsStream("/data.sql"), StandardCharsets.UTF_8));

        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }
    }

    @Test
    public void testLogOperationMapper() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LogOperationMapper mapper = sqlSession.getMapper(LogOperationMapper.class);
            LogOperationPageDTO dto = new LogOperationPageDTO();

            List<LogOperationVO> results = mapper.pageList(dto);

            assertNotNull(results);
            results.forEach(result -> {
                System.out.println(result);
            });
        }
    }
}
