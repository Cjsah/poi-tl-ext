/*
 * Copyright 2016 - 2021 Draco, https://github.com/draco1023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ddr.poi.html;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.ddr.poi.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

class HtmlRenderPolicyTest {

    @Test
    void doRender() throws IOException {
//        String input = "/study-template-word.docx";
//        String output = "result2.docx";

        String input = "/4.docx";
        String output = "result1.docx";


        HtmlRenderConfig renderConfig = new HtmlRenderConfig();
        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy(renderConfig);
        Configure config = Configure.builder()
                .bind("article", htmlRenderPolicy)
                .build();

        Map<String, Object> data = new HashMap<>();

        data.put("article", FileReader.readFile("/4.html"));

        long start = System.currentTimeMillis();

        try (
                InputStream inputStream = HtmlRenderPolicyTest.class.getResourceAsStream(input);
                XWPFTemplate wordTemplate = XWPFTemplate.compile(inputStream, config).render(data);
        ) {
            wordTemplate.writeToFile(output);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}