package com.outsourcebooster.cryptocurrency.web.model.loan

enum class LoanStatus {
    waiting_for_user_agreement,
    removed,
    waiting_for_pledge,
    ready_to_receive,
    overdue,
    closed
}