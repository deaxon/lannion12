class Student < ActiveRecord::Base
  attr_accessible :cip, :inactif, :studentFirstName, :studentName

  has_many :projects, :through => :trimester
  has_many :trimester

  validates_uniqueness_of :cip
  validates_presence_of :cip
  validates_presence_of :studentName
  validates_presence_of :studentFirstName

  validates_format_of :cip, :with => /\D{4}\d{4}/, :message => ': doit contenir 4 lettres suivies de 4 chiffres'

  def enrolled_in?(project)
    self.projects.include?(project)
  end
  def unenrolled_projects
    Project.all - self.projects
  end
end
