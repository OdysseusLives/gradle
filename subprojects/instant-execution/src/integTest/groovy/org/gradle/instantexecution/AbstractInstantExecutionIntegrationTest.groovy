/*
 * Copyright 2019 the original author or authors.
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

package org.gradle.instantexecution

import org.gradle.integtests.fixtures.AbstractIntegrationSpec
import org.gradle.integtests.fixtures.BuildOperationsFixture
import org.gradle.integtests.fixtures.DefaultTestExecutionResult
import org.gradle.integtests.fixtures.instantexecution.InstantExecutionBuildOperationsFixture
import org.intellij.lang.annotations.Language


class AbstractInstantExecutionIntegrationTest extends AbstractIntegrationSpec {

    void buildKotlinFile(@Language("kotlin") String script) {
        buildKotlinFile << script
    }

    void instantRun(String... args) {
        run(INSTANT_EXECUTION_PROPERTY, *args)
    }

    void instantFails(String... args) {
        fails(INSTANT_EXECUTION_PROPERTY, *args)
    }

    public static final String INSTANT_EXECUTION_PROPERTY = "-Dorg.gradle.unsafe.instant-execution=true"

    protected InstantExecutionBuildOperationsFixture newInstantExecutionFixture() {
        return new InstantExecutionBuildOperationsFixture(new BuildOperationsFixture(executer, temporaryFolder))
    }

    protected void assertTestsExecuted(String testClass, String... testNames) {
        new DefaultTestExecutionResult(testDirectory)
            .testClass(testClass)
            .assertTestsExecuted(testNames)
    }
}
