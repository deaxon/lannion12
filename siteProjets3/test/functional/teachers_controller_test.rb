require 'test_helper'

class TeachersControllerTest < ActionController::TestCase
  setup do
    @student = teachers(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:teachers)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create teacher" do
    assert_difference('Teacher.count') do
      post :create, student: { cipTeacher: @student.cipTeacher, inactifTeacher: @student.inactifTeacher, teacherFirstName: @student.teacherFirstName, teacherName: @student.teacherName }
    end

    assert_redirected_to teacher_path(assigns(:student))
  end

  test "should show teacher" do
    get :show, id: @student
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @student
    assert_response :success
  end

  test "should update teacher" do
    put :update, id: @student, student: { cipTeacher: @student.cipTeacher, inactifTeacher: @student.inactifTeacher, teacherFirstName: @student.teacherFirstName, teacherName: @student.teacherName }
    assert_redirected_to teacher_path(assigns(:student))
  end

  test "should destroy teacher" do
    assert_difference('Teacher.count', -1) do
      delete :destroy, id: @student
    end

    assert_redirected_to teachers_path
  end
end
