class CreateSemesters < ActiveRecord::Migration
  def change
    create_table :semesters do |t|
      t.integer :teacher_id
      t.integer :project_id
      t.string :semesterTeacher

      t.timestamps
    end
  end
  def self.down
    drop_table :semesters
  end
end
