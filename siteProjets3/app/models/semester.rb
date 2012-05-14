class Semester < ActiveRecord::Base
  attr_accessible :project_id, :semesterTeacher, :teacher_id

    belongs_to :teacher
    belongs_to :project

    def getNameProject
      return Project.find(project_id).projectName
    end
    def getTeacherName
      if Teacher.find(teacher_id).inactifTeacher == FALSE
        return Teacher.find(teacher_id).teacherName
      end
    end
    def getTeacherFirstName
      if Teacher.find(teacher_id).inactifTeacher == FALSE
        return Teacher.find(teacher_id).teacherFirstName
      end
    end

end
