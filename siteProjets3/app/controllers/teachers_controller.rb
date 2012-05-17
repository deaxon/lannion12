class TeachersController < ApplicationController
  # GET /teachers
  # GET /teachers.json
  before_filter :authorize
  def projects
    @teacher = Teacher.find(params[:id])
    @projects = @teacher.projects
    @semesters = Semester.find(:all, :conditions => { :teacher_id => @teacher, :project_id => @projects})
  end

  def publier
    @teacher = Teacher.find(params[:id])
    @teacher.inactif = FALSE
    @teacher.save
    redirect_to(teachers_path)
  end

  def horsligne
    @teacher = Teacher.find(params[:id])
    @teacher.inactif = TRUE
    @teacher.save
    redirect_to(teachers_path)
  end

  def project_add
    @teacher = Teacher.find(params[:id])
    @project = Project.find(params[:project])


    unless @teacher.enrolled_teacher_in?(@project)
      @teacher.projects << @project

      @semester = Semester.find(:last)
      @sem = params[:semesterTeacher]
      @semester.semesterTeacher = @sem
      @semester.save

      flash[:notice] = 'Project add.'
    else
      flash[:notice] = 'Teacher already on the project !'
    end
    redirect_to :action => :projects, :id => @teacher
  end

  def project_remove
    @teacher = Teacher.find(params[:id])
    project_ids = params[:projects]

    unless project_ids.blank?
      project_ids.each do |project_id|
        project = Project.find(project_id)
        if @teacher.enrolled_teacher_in?(project)
          logger.info "Remove teacher of the project #{project.id}"
          @teacher.projects.delete(project)
          flash[:notice] = 'Projet enleve.'
        end
      end
    end
    redirect_to :action => :projects, :id => @teacher
  end

  def index
    @teachers = Teacher.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @teachers }
    end
  end

  # GET /teachers/1
  # GET /teachers/1.json
  def show
    @teacher = Teacher.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @teacher }
    end
  end

  # GET /teachers/new
  # GET /teachers/new.json
  def new
    @teacher = Teacher.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @teacher }
    end
  end

  # GET /teachers/1/edit
  def edit
    @teacher = Teacher.find(params[:id])
  end

  # POST /teachers
  # POST /teachers.json
  def create
    @teacher = Teacher.new(params[:teacher])

    respond_to do |format|
      if @teacher.save
        format.html { redirect_to @teacher, notice: 'Teacher was successfully created.' }
        format.json { render json: @teacher, status: :created, location: @teacher }
      else
        format.html { render action: "new" }
        format.json { render json: @teacher.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /teachers/1
  # PUT /teachers/1.json
  def update
    @teacher = Teacher.find(params[:id])

    respond_to do |format|
      if @teacher.update_attributes(params[:teacher])
        format.html { redirect_to @teacher, notice: 'Teacher was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @teacher.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /teachers/1
  # DELETE /teachers/1.json
  def destroy
    @teacher = Teacher.find(params[:id])
    @teacher.destroy

    respond_to do |format|
      format.html { redirect_to teachers_url }
      format.json { head :no_content }
    end
  end
end
