class CreateTeachers < ActiveRecord::Migration
  def change
    create_table :teachers do |t|
      t.string :teacherName
      t.string :teacherFirstName
      t.string :cipTeacher
      t.boolean :inactifTeacher

      t.timestamps
    end
  end
  def self.down
    drop_table :teachers
  end
end
