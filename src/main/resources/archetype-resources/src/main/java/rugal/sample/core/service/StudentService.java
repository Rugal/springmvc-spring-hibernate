/*
 * Copyright 2014 rugal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rugal.sample.core.service;

import rugal.common.page.Pagination;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
public interface StudentService
{

    Student deleteById(Integer id);

    Student findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Student save(Student bean);

    Student update(Student bean);

}