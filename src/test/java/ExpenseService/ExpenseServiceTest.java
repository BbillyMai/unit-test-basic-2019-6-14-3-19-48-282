package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.INTERNAL, "internal");
        // when
        ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertSame(ExpenseType.INTERNAL_PROJECT_EXPENSE, expenseType);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project_a = new Project(ProjectType.EXTERNAL, "Project A");
        // when
        ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project_a);
        // then
        assertSame(ExpenseType.EXPENSE_TYPE_A, expenseType);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project_b = new Project(ProjectType.EXTERNAL, "Project B");
        // when
        ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project_b);
        // then
        assertSame(ExpenseType.EXPENSE_TYPE_B, expenseType);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project_c = new Project(ProjectType.EXTERNAL, "Project C");
        // when
        ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project_c);
        // then
        assertSame(ExpenseType.OTHER_EXPENSE, expenseType);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project unexpectedProject = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "Unexpected Project");

        assertThrows(UnexpectedProjectTypeException.class, () -> ExpenseService.getExpenseCodeByProjectTypeAndName(unexpectedProject));
    }
}