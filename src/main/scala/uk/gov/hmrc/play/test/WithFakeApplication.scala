/*
 * Copyright 2015 HM Revenue & Customs
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

package uk.gov.hmrc.play.test

import org.scalatest.{BeforeAndAfterAll, Suite}
import play.api.test.FakeApplication
import play.api.Play
import play.api.test.Helpers._

/**
 * Use this instead of play.test.WithApplication
 *
 * WithApplication will bring in specs2 lifecyles and changes the test run behaviour
 */
trait WithFakeApplication extends BeforeAndAfterAll {
  this: Suite =>

  lazy val fakeApplication = FakeApplication()

  override def beforeAll() {
    super.beforeAll()
    Play.start(fakeApplication)
  }

  override def afterAll() {
    super.afterAll()
    Play.stop()
  }

  def evaluateUsingPlay[T](block: => T): T = {
    running(fakeApplication) {
      block
    }
  }

}
