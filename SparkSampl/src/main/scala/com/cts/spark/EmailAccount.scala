package com.cts.spark

import scala.beans.BeanProperty

class EmailAccount {
  @BeanProperty var accountName: String = null
  @BeanProperty var username: String = null
  @BeanProperty var password: String = null
  @BeanProperty var mailbox: String = null
  @BeanProperty var imapServerUrl: String = null
  @BeanProperty var minutesBetweenChecks: Int = 0
  @BeanProperty var protocol: String = null
  @BeanProperty var usersOfInterest = new java.util.ArrayList[String]()
  
  override def toString: String = {
    return String.format("acct (%s), user (%s), url (%s), password(%s)", accountName, username, imapServerUrl, password)
  }
}