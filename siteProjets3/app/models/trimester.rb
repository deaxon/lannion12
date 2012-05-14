class Trimester < ActiveRecord::Base
  attr_accessible :project_id, :semester, :student_id
  belongs_to :student
  belongs_to :project

  def getNameProject
    return Project.find(project_id).projectName
  end
  def getStudentName
    if Student.find(student_id).inactif == FALSE
  return Student.find(student_id).studentName
    end
  end
  def getStudentFirstName
    if Student.find(student_id).inactif == FALSE
  return Student.find(student_id).studentFirstName
    end
  end
end
