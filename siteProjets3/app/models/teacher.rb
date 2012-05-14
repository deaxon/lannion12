class Teacher < ActiveRecord::Base
  attr_accessible :cipTeacher, :inactifTeacher, :teacherFirstName, :teacherName

  has_many :projects, :through => :semesters
  has_many :semesters 

  validates_uniqueness_of :cipTeacher 
  validates_presence_of :cipTeacher
  validates_presence_of :teacherName
  validates_presence_of :teacherFirstName

  #validates_format_of :cip, :with => /\D{4}+\d{4}/, :message => ': doit contenir 4 lettres suivies de 4 chiffres'

  def enrolled_teacher_in?(project)
    self.projects.include?(project)
  end
  def unenrolled_teacher_projects
    Project.all - self.projects
  end
end
